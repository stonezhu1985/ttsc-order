package com.ttsc.data.action.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttsc.data.action.BaseController;
import com.ttsc.data.entity.account.Account;
import com.ttsc.data.entity.account.InvitationCode;
import com.ttsc.data.entity.account.PhoneRegistInfo;
import com.ttsc.data.entity.account.UserShopBindInfo;
import com.ttsc.data.po.AccountForm;
import com.ttsc.data.po.AccountPo;
import com.ttsc.data.po.UserShopForm;
import com.ttsc.data.result.BasicResult;
import com.ttsc.data.service.account.AccountService;
import com.ttsc.data.service.account.InvitationCodeService;
import com.ttsc.data.service.account.PhoneRegistService;
import com.ttsc.data.service.account.UserShopBindService;
import com.ttsc.data.util.Constant;
import com.ttsc.data.util.DateUtil;
import com.ttsc.data.util.SMSSender;
import com.ttsc.data.util.StringUtil;

/**
 * 商户用户注册、登录
 * 
 * @author arno.jiang
 * 
 */
@Controller
@RequestMapping("account")
public class AccountController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private InvitationCodeService invitationCodeService;

	@Autowired
	private PhoneRegistService phoneRegistService;
	
	@Autowired
	private UserShopBindService userShopBindService;

	/**
	 * 商户用户注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "registAccount", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<String> registAccount(HttpServletRequest request,@RequestBody AccountForm form) throws Exception {
		BasicResult<String> rs = new BasicResult<String>();
		try {
			String telephone = form.getTelephone();
			String password = form.getPassword();
			String surePwd = form.getSurePwd();
			String invitationCode = form.getInvitationCode();// 邀请码
			String validCode = form.getValidCode();// 验证码
			String phoneValidCode = form.getPhoneValidCode();// 手机验证码

			// 验证手机号码
			if (!StringUtil.isMobile(telephone)) {
				rs.setMessage("由于手机号不符合规则注册失败!");
				rs.setCode("1");
				return rs;
			}

			// 验证两次密码输入是否一致
			if (StringUtils.isEmpty(password)) {
				rs.setMessage("密码不能为空，请输入!");
				rs.setCode("1");
				return rs;
			}

			if (!password.equals(surePwd)) {
				rs.setMessage("两次密码输入不一致!");
				rs.setCode("1");
				return rs;
			}
			
			//验证码
			if(StringUtils.isEmpty(validCode)){
				rs.setMessage("验证码不能为空，请输入!");
				rs.setCode("1");
				return rs;
			}
			
			if(!validCode.equalsIgnoreCase(request.getSession().getAttribute(Constant.VALID_CODE_INFO).toString())){
				rs.setMessage("验证码错误，请重新输入!");
				rs.setCode("1");
				return rs;
			}

			// 验证该手机号是否已经注册过
			int count = accountService.findAccountByPhone(telephone);
			if (count > 0) {
				rs.setMessage("该手机号已注册，请直接登录!");
				rs.setCode("1");
				return rs;
			}

			// 手机验证码校验
			PhoneRegistInfo phoneRegistInfo = new PhoneRegistInfo();
			phoneRegistInfo.setTelephone(telephone);
			phoneRegistInfo.setType(1);
			phoneRegistInfo.setRegistCode(phoneValidCode);
			phoneRegistInfo.setStatus(0);
			PhoneRegistInfo registInfo = phoneRegistService
					.findPhoneRegistDaoByPhoneAndRegistCode(phoneRegistInfo);
			if (registInfo == null) {
				rs.setMessage("手机验证码校验失败，请重新输入!");
				rs.setCode("1");
				return rs;
			}

			// 如有邀请码，验证其是否存在
			if (StringUtils.isNotEmpty(invitationCode)) {
				InvitationCode codeObj = invitationCodeService
						.findInvitationByInvitationCode(invitationCode);
				if (codeObj == null) {
					rs.setMessage("该邀请码不存在，请重新输入!");
					rs.setCode("1");
					return rs;
				}
			}

			if(invitationCode.isEmpty()){
				invitationCode = "0";
			}
			// 保存账号信息
			Account account = new Account();
			account.setTelephone(telephone);
			account.setQq("");
			account.setPassword(password);
			account.setInvitationCode(Integer.parseInt(invitationCode));
			account.setRegTime(new Date());
			int userId = accountService.saveUser(account);

			// 生成邀请码
			int maxInvitationCode = invitationCodeService
					.findMaxInvitationCode() + 1;

			// 保存账号关联的邀请码信息
			InvitationCode code = new InvitationCode();
			code.setUserId(userId);
			code.setType(1);
			code.setCode(maxInvitationCode + "");
			invitationCodeService.saveInvitationCode(code);

			// 修改手机验证码验证时间及验证状态
			registInfo.setStatus(1);
			registInfo.setRegistTime(new Date());
			phoneRegistService.updatePhoneRegistInfo(registInfo);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，注册失败!" + e.getMessage());
			rs.setMessage("系统异常，注册失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userLogin", method=RequestMethod.POST)
	@ResponseBody
	public BasicResult<Account> userLogin(HttpServletRequest request,@RequestBody AccountForm form) throws Exception {
		BasicResult<Account> rs = new BasicResult<Account>();
		try {
			String telephone = form.getTelephone();
			String password = form.getPassword();

			if (StringUtils.isEmpty(telephone)) {
				rs.setMessage("账号不能为空!");
				rs.setCode("1");
				return rs;
			}

			if (StringUtils.isEmpty(password)) {
				rs.setMessage("密码不能为空，请输入!");
				rs.setCode("1");
				return rs;
			}

			Account account = new Account();
			account.setTelephone(telephone);
			account.setPassword(password);
			Account obj = accountService.getAccountByPhoneAndPwd(account);
			if (obj == null) {
				rs.setMessage("账号或密码错误，请重新输入!");
				rs.setCode("1");
				return rs;
			}
			rs.setSingleResult(obj);
			request.getSession()
					.setAttribute(Constant.USER_LOGIN_INFO, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，登录失败!" + e.getMessage());
			rs.setMessage("系统异常，登录失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 用户退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userLogout", method = {RequestMethod.POST })
	@ResponseBody
	public BasicResult<Account> userLogout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<Account> rs = new BasicResult<Account>();
		try {
			request.getSession().removeAttribute(Constant.USER_LOGIN_INFO);
		} catch (Exception e) {
			logger.info("系统异常，账户退出失败!" + e.getMessage());
			rs.setMessage("系统异常，账户退出失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}

	/**
	 * 获取网页验证码
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value = "getPageValidCode", method = { RequestMethod.GET })
	public void getPageValidCode(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		int width = 90;// 定义图片的width
		int height = 20;// 定义图片的height
		int codeCount = 4;// 定义图片上显示验证码的个数
		int xx = 15;
		int fontHeight = 18;
		int codeY = 16;
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * xx, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		System.out.print(randomCode);
		session.setAttribute(Constant.VALID_CODE_INFO, randomCode.toString());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}

	/**
	 * 忘记密码、重设密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "resetAccountPwd", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<String> resetAccountPwd(HttpServletRequest request,@RequestBody AccountForm form) throws Exception {
		BasicResult<String> rs = new BasicResult<String>();
		try {
			String telephone = form.getTelephone();
			String password = form.getPassword();
			String surePwd = form.getSurePwd();
			String validCode = form.getValidCode();// 验证码
			String phoneValidCode = form.getPhoneValidCode();// 手机验证码

			// 验证手机号码
			if (!StringUtil.isMobile(telephone)) {
				rs.setMessage("对不起,手机号码不符合规则!");
				rs.setCode("1");
				return rs;
			}

			// 验证两次密码输入是否一致
			if (StringUtils.isEmpty(password)) {
				rs.setMessage("对不起,密码不能为空!");
				rs.setCode("1");
				return rs;
			}

			if (!password.equals(surePwd)) {
				rs.setMessage("对不起,两次密码输入不一致!");
				rs.setCode("1");
				return rs;
			}
			
			//验证码
			if(StringUtils.isEmpty(validCode)){
				rs.setMessage("验证码不能为空，请输入!");
				rs.setCode("1");
				return rs;
			}
			
			if(!validCode.equalsIgnoreCase(request.getSession().getAttribute(Constant.VALID_CODE_INFO).toString())){
				rs.setMessage("验证码错误，请重新输入!");
				rs.setCode("1");
				return rs;
			}

			// 手机验证码校验
			PhoneRegistInfo phoneRegistInfo = new PhoneRegistInfo();
			phoneRegistInfo.setTelephone(telephone);
			phoneRegistInfo.setType(1);
			phoneRegistInfo.setRegistCode(phoneValidCode);
			phoneRegistInfo.setStatus(0);
			PhoneRegistInfo registInfo = phoneRegistService
					.findPhoneRegistDaoByPhoneAndRegistCode(phoneRegistInfo);
			if (registInfo == null) {
				rs.setMessage("手机验证码校验失败，请重新输入!");
				rs.setCode("1");
				return rs;
			}

			// 验证该手机号是否已经注册过
			int count = accountService.findAccountByPhone(telephone);
			if (count == 0) {
				rs.setMessage("该手机号码尚未注册过账号!");
				rs.setCode("1");
				return rs;
			}

			// 更改账号密码
			Account account = new Account();
			account.setTelephone(telephone);
			account.setPassword(password);
			accountService.updateAccountInfo(account);

			// 修改手机验证码验证时间及验证状态
			registInfo.setStatus(1);
			registInfo.setRegistTime(new Date());
			phoneRegistService.updatePhoneRegistInfo(registInfo);

		} catch (Exception e) {
			logger.info("系统异常，重置登录密码失败!" + e.getMessage());
			rs.setMessage("系统异常，重置登录密码失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateAccountPwd", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<String> updateAccountPwd(HttpServletRequest request,@RequestBody AccountForm form) throws Exception {
		BasicResult<String> rs = new BasicResult<String>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			String telephone = form.getTelephone();
			String oldPwd = form.getOldPwd();
			String password = form.getPassword();
			String surePwd = form.getSurePwd();

			if (StringUtils.isEmpty(telephone)) {
				rs.setMessage("账号不能为空!");
				rs.setCode("1");
				return rs;
			}

			if (StringUtils.isEmpty(oldPwd)) {
				rs.setMessage("原始密码不能为空，请输入!");
				rs.setCode("1");
				return rs;
			}

			// 验证两次密码输入是否一致
			if (StringUtils.isEmpty(password)) {
				rs.setMessage("新密码不能为空，请输入!");
				rs.setCode("1");
				return rs;
			}

			if (!password.equals(surePwd)) {
				rs.setMessage("两次新密码输入不一致，请调整!");
				rs.setCode("1");
				return rs;
			}

			Account account = new Account();
			account.setTelephone(telephone);
			account.setPassword(oldPwd);
			Account obj = accountService.getAccountByPhoneAndPwd(account);
			if (obj == null) {
				rs.setMessage("原始密码错误，请重新输入!");
				rs.setCode("1");
				return rs;
			}

			// 更改账号密码
			account.setPassword(password);
			accountService.updateAccountInfo(account);

		} catch (Exception e) {
			logger.info("系统异常，修改登录密码失败!" + e.getMessage());
			rs.setMessage("系统异常，修改登录密码失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPhoneValidCode", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})  

	@ResponseBody
	public BasicResult<String> getPhoneValidCode(HttpServletRequest request,@RequestBody AccountForm form) throws Exception {
		BasicResult<String> rs = new BasicResult<String>();
		try {
			String telephone = form.getTelephone();
			System.out.println("telephone="+telephone);

			// 验证手机号码
			if (!StringUtil.isMobile(telephone)) {
				rs.setMessage("对不起,由于手机号不符合规则验证码生成失败!");
				rs.setCode("1");
				return rs;
			}
			// 随机6位数验证码
			String registCode = StringUtil.buildValidateCode(6, 0);
			
			//发送短信
			Map<String,String> map = SMSSender.sendMailProperty(registCode,telephone);
			SMSSender.urlPost("", map);

			PhoneRegistInfo phoneRegistInfo = new PhoneRegistInfo();
			phoneRegistInfo.setTelephone(telephone);
			phoneRegistInfo.setType(1);
			phoneRegistInfo.setRegistCode(registCode);

			phoneRegistService.savePhoneRegistInfo(phoneRegistInfo);
			rs.setMessage("手机验证码短信发送成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，获取手机验证码失败!" + e.getMessage());
			rs.setMessage("系统异常，获取手机验证码失败!");
			rs.setCode("1");
			return rs;
		}

		return rs;
	}
	
	/**
	 * 绑定商铺
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "bindShop", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<List<UserShopBindInfo>> bindShop(HttpServletRequest request,@RequestBody UserShopForm form) throws Exception {
		BasicResult<List<UserShopBindInfo>> rs = new BasicResult<List<UserShopBindInfo>>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			
			Account account = (Account)request.getSession().getAttribute(Constant.USER_LOGIN_INFO);
			int userId = account.getId();
			
			String platId = form.getPlatId();
			String shopName = form.getShopName();
			String linkUrl = form.getLinkUrl();
			String validCode = form.getValidCode();
			String wwId = form.getWwId();
			String province = form.getProvince();
			String city = form.getCity();
			String town = form.getTown();

			if (StringUtils.isEmpty(platId)) {
				rs.setMessage("请选择绑定平台!");
				rs.setCode("1");
				return rs;
			}
			
			if (StringUtils.isEmpty(shopName)) {
				rs.setMessage("请输入店铺名称!");
				rs.setCode("1");
				return rs;
			}
			
			if (StringUtils.isEmpty(linkUrl)) {
				rs.setMessage("店铺首页网址!");
				rs.setCode("1");
				return rs;
			}

			UserShopBindInfo userShopBindInfo = new UserShopBindInfo();
			userShopBindInfo.setUserId(userId);
			userShopBindInfo.setPlatId(Integer.parseInt(platId));
			userShopBindInfo.setShopName(shopName);
			userShopBindInfo.setLinkUrl(linkUrl);
			userShopBindInfo.setValidCode(validCode);
			userShopBindInfo.setWwId(wwId==null?"":wwId);
			userShopBindInfo.setProvince(Integer.parseInt(province));
			userShopBindInfo.setCity(Integer.parseInt(city));
			userShopBindInfo.setTown(Integer.parseInt(town));
			userShopBindInfo.setStatus(0);
			userShopBindInfo.setCreateTime(new Date());
			
			userShopBindService.saveUserShopBind(userShopBindInfo);
			
			List<UserShopBindInfo> list = userShopBindService.getUserShopBindList(userId);
			rs.setSingleResult(list);
		} catch (Exception e) {
			logger.info("系统异常，账户退出失败!" + e.getMessage());
			rs.setMessage("系统异常，账户退出失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 获取账号信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAccountInfo", method = {RequestMethod.POST })
	@ResponseBody
	public BasicResult<AccountPo> getAccountInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<AccountPo> rs = new BasicResult<AccountPo>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			
			Account account = (Account)request.getSession().getAttribute(Constant.USER_LOGIN_INFO);
			int userId = account.getId();
			
			List<UserShopBindInfo> list = userShopBindService.getUserShopBindList(userId);
			
			AccountPo po = new AccountPo();
			po.setId(account.getId());
			po.setTelephone(account.getTelephone());
			po.setQq(account.getQq());

			po.setRegTime(DateUtil.getDateFormatter(account.getRegTime(), "yyyy-MM-dd"));
			po.setList(list);
			rs.setSingleResult(po);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，获取账户信息失败!" + e.getMessage());
			rs.setMessage("系统异常，获取账户信息失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	@RequestMapping(value = "getCheckedShop", method = {RequestMethod.POST })
	@ResponseBody
	public BasicResult<JSONObject> getCheckedShop(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<JSONObject> rs = new BasicResult<JSONObject>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			
			Account account = (Account)request.getSession().getAttribute(Constant.USER_LOGIN_INFO);
			int userId = account.getId();
			
			List<UserShopBindInfo> list = userShopBindService.getPassedUserShopBindList(userId);
			
			JSONArray jt  = new JSONArray();
			JSONArray tb = new JSONArray();
			JSONObject obj = null;
			UserShopBindInfo bindInfo = null;
			for(int i=0;i<list.size();i++){
				bindInfo = list.get(i);
				obj = new JSONObject();
				obj.put("id", bindInfo.getId());
				obj.put("name", bindInfo.getShopName());
				if(bindInfo.getPlatId() == 1){
					tb.add(obj);
				}else {
					jt.add(obj);
				}
			}
			obj = new JSONObject();
			obj.put("tb", tb);
			obj.put("jt", jt);
			rs.setSingleResult(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，获取账户商铺绑定信息失败!" + e.getMessage());
			rs.setMessage("系统异常，获取账户商铺绑定信息失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}

}
