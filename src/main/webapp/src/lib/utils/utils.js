/**
 * 对日期操作的公共方法
 */
var DateUtil = DateUtil || {};
/**
 * 对数据操作的公共方法
 */
var DataUtil = DataUtil || {};
/**
 * 对页面元素操作的公共方法
 */
var FormUtil = FormUtil || {};


(function(win){
    var arr = ['_contains', 'Trim', 'isNull', 'isDigit', 'isNumber', 'isCurrency', 'isInteger', 'remove', 'removeBinEnd','replaceAll', 'delSpeStr', 'toArray'];
    for(var i = 0; i < arr.length ;i++){
        if(String.prototype.hasOwnProperty(arr[i])){
            win.console.error('Utils.js ：String 对象已含有' + arr[i] + '  属性, 使用请重载，以免冲突 ');
        }
    };
})(window);

//是否包含
String.prototype._contains = function (a) {
    return !!(~this.indexOf(a));
};
//去空格
String.prototype.Trim = function() {
    if(this != null && typeof(this) != 'undefined' && this.length > 0) {
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
};
//功能:判断元素的值是否为空
String.prototype.isNull = function() {
    return (this == null || this.Trim() == '' || typeof(this) == 'undefined' || this.length == 0);
};
// 检查是否由数字组成
String.prototype.isDigit = function() {
    var s = this.Trim();
    return (s.replace(/\d/g, "").length == 0);
};
// 检查是否为数字
String.prototype.isNumber = function() {
    var s = this.Trim();
    return (s.search(/^[+-]?[0-9.]*$/) >= 0);
};
// 检查是否为货币格式
String.prototype.isCurrency = function() {
    var s = this.Trim();
    return (s.search(/^[0-9]+([.]\d{1,2})?$/) >= 0);
};
// 检查是否由整数
String.prototype.isInteger = function(){
    var s = this.Trim();
    var p = /^[-\+]?\d+$/;
    return p.test(s);
};
//删除指定位置的字符,指定个数字符
String.prototype.remove = function(start, length){
    var l = this.slice(0, start);
    var r = this.slice(start+length);
    return l+r;
};
//删除第一个和最后一个字符
String.prototype.removeBinEnd = function(){
    var str  = this.remove(0, 1);
    str = str.remove(str.length - 1, 1);
    return str;
};
String.prototype.replaceAll = function(str1, str2){
    if (this.isNull()) {
        return this;
    }
    return this.split(str1).join(str2);
}
//删除指定字符串
String.prototype.delSpeStr = function(a){
    return this.replaceAll(a,"");
};
String.prototype.toArray = function(a){
    var arr = [];
    if(this._contains(a)){
        arr = this.split(a);
    }else{
        arr.push(this);
    }
    return arr;
};

/**
 * 提示信息
 * @param content
 * @param param
 */
main.alert = {
    message : function(content, param){
        $.bootstrapGrowl(content, param);
    },
    success : function(content){
        main.alert.message(content,  { type: 'success' });
    },
    error : function(content){
        main.alert.message(content,  { type: 'danger' });
    },
    showSuccess : function(content){
        FormUtil.showOverlay('show');
        main.alert.message(content,  { type: 'success' });
        setTimeout(function() {
            FormUtil.showOverlay('hide');
        }, 3000);
    },
    showError : function(content){
        FormUtil.showOverlay('show');
        main.alert.message(content,  { type: 'danger' });
        setTimeout(function() {
            FormUtil.showOverlay('hide');
        }, 3000);
    }
};

main.log = function(){
    if(main.dev){
        for( var i = 0; i < arguments.length; i++ ){
            if(DataUtil.is.Object(arguments[i])){
                console.dir(arguments[i]);
                console.log('\n');
            }else{
                console.log(arguments[i] + '\n');
            }
        }
    }
}

main.error = function(){
    if(localStorage){
        if(localStorage.getItem('debug')){
            main.dev = true;
        }else{
            main.dev = false;
        }
    }
    if(!main.dev){
        if(window.location.search.toLowerCase().indexOf('debug') > -1){
            localStorage.setItem('debug', true);
            main.dev = true;
        }
    }
    if(main.dev){
        for( var i = 0; i < arguments.length; i++ ){
            if(DataUtil.is.Object(arguments[i])){
                console.dir(arguments[i]);
                console.error('\n');
            }else{
                console.error(arguments[i] + '\n');
            }
        }
    }
}



main.toUrl = function(url){
    window.location.href = main.base_url + url;
}

main.ajaxMsg = function(rep, url){
    if(rep && rep.code == 0){
        if(rep.message){
            main.alert.showSuccess(rep.message);
        }
        if(url){
            setTimeout(function(){
                window.location.href = main.base_url + url;
            },3000);
        }
    }else if(rep && rep.code == 1){
        if(rep.message){
            main.alert.showSuccess(rep.message);
        }
    }
}

main.ajaxConfirm = function(){

}


main.getActionUrl = function (url) {
    return this.ajax_url + url;
}

/**
 * aja 依赖jquery 根据url发送ajax请求
 * @param url
 * @param data : 参数
 * @param callBack ：回调函数
 */

main.getAjaxUrl = function(type, address){
    var postfix = "";
    if (window.location.origin.toLowerCase().indexOf("localhost") != -1) {
        type = 'GET';
        postfix = ".json";
    }

    if(window.location.search.toLowerCase().indexOf('dev') > -1){
        var params = DataUtil.urlGet();
        main.dev = params['dev'];
        localStorage.setItem('dev', main.dev);
        if(main.dev == 1){
            main.ajax_url = main.base_url  + 'mock/';
        }else{
            main.ajax_url = main.base_url;
        }
    }else{
        var dev = localStorage.getItem('dev');
        if(dev){
            main.dev = dev;
            if(main.dev == 1){
                main.ajax_url = main.base_url  + 'mock/';
            }else{
                main.ajax_url = main.base_url;
            }
        }
    }

    return {url : main.ajax_url + address + postfix, type : type};
}

main.progressStart = function () {
    $('body').showLoading();
}
main.progressClose = function () {
    $('body').hideLoading();
}

main.ajax = function (type, address, data, callBack) {
    var d = this.getAjaxUrl(type, address);
    var url = d.url, type = d.type;
    var json = JSON.stringify(data);
    main.log(type + " : " + url + " \n" + json);
    $.ajax(
        {
            type: type,
            url: url + "?uid=" + new Date(),
            data: json,
            dataType:"json",
            contentType:"application/json",
            timeout: 15000,
            beforeSend : main.progressStart(),
            error: function (e) {
                console.error('error 加载异常：' + url);
                console.error(e);
            },
            success: function (result) {
                try {
                    main.log("result ===>", result);
                    callBack(result);
                } catch (e) {
                    console.error('success 加载异常：' + url);
                }
            },
            complete: function (e, t) {
                if (t && t == "timeout") {
                    console.error('complete 请求超时：' + url);
                    callBack(t);
                }
            }
        }
    )
};

main.post = function(address, data, callBack){
    try{
       // main.progressStart();
        main.ajax("POST", address, data, callBack);
    }catch(e){
    }finally{
        main.progressClose();
    }

}

main.get = function(address, data, callBack){
    try{
       // main.progressStart();
        main.ajax("GET", address, data, callBack);
    }catch(e){
    }finally{
       main.progressClose();
    }
}

/****************************  DataUtil bin  ****************************/
/**
 * 检测各种具体是对象类型
 */
DataUtil.is ={types : ["Array", "Boolean", "Date", "Number", "Object", "RegExp", "String", "Window", "HTMLDocument"]}
for(var i = 0, c; c = DataUtil.is.types[i ++ ]; ){
    DataUtil.is[c] = (function(type){
        return function(obj){
            return Object.prototype.toString.call(obj) == "[object " + type + "]";
        }
    })(c);
}

DataUtil.getVal = function(nd){
    var obj = $("#" + nd);
    try{
        if(obj && obj.length > 0){
            return gitSwitch(obj[0]);
        }else{
            return gitSwitch(document.getElementsByName(nd)[0]);
        }
    }catch(e){
        main.error(id + 'is no exsit!!');
    }

    function gitSwitch(obj){
        switch(obj.type.toLowerCase()){
            case 'checkbox' : return FormUtil.getChecked(nd);
                break;
            case 'radio' : return FormUtil.getRadio(nd);
                break;
            case 'text' : return $.trim(obj.value);
                break;
            case 'password' : return $.trim(obj.value);
                break;
            case 'select-one' : return FormUtil.getselected(obj);
                break;
            case 'select-multiple':return FormUtil.getselected(obj);
                break;
        }
    }

    return "";
}

DataUtil.getRsJson = function(arr){
    var json = {};
    $.each(arr,function(i, o) {
        json[o] = DataUtil.getVal(o);
    })
    return json;
}

DataUtil.setVal = function(nd, val){
    var obj = $("#" + nd);
    try{
        if(obj && obj.length > 0){
            gitSwitch(obj[0]);
        }else{
            gitSwitch(document.getElementsByName(nd)[0]);
        }
    }catch(e){
        main.error(nd + 'is no exsit!!');
    }

    function gitSwitch(obj){
        switch(obj.type.toLowerCase()){
            case 'checkbox' : ''
                break;
            case 'radio' :
                break;
            case 'text' : obj.value = val;;
                break;
            case 'password' : ''
                break;
            case 'select-one' :''
                break;
            case 'select-multiple':''
                break;
        }
    }
}

DataUtil.setVals = function(){

}



/**
 * 获取URL参数
 */

DataUtil.urlGet = function() {
    var aQuery = window.location.href.split("?");
    var aGET = {};
    if(aQuery.length > 1) {
        var aBuf = aQuery[1].split("&");
        for(var i=0, iLoop = aBuf.length; i < iLoop; i++) {
            var aTmp = aBuf[i].split("=");
            aGET[aTmp[0]] = aTmp[1];
        }
    }
    return aGET;
}

/**
 * 产生随机数
 * @param index
 */
DataUtil.mathRand = function (index) {
    var num = "";
    for (var i = 0; i < index; i++) {
        num += Math.floor(Math.random() * 10);
    }
    return num;
}
    /****************************  DataUtil end  ****************************/
/****************************** Radio bin ******************************/
/**
 * 创建Radio bootstrap
 * @param id
 * @param name
 * @param json
 */
FormUtil.createRadio = function (id, name, json, checkVal) {
    var rarios = [];
    $.each(json, function (n, value) {
        rarios.push('<label class=\"radio\">');
        if(checkVal){
            rarios.push('<input type=\"radio\" name=\"' + name + '\" value=\"' + value.id + '\"' + (value.id==checkVal?'checked>':'>'));
        }else{
            rarios.push('<input type=\"radio\" name=\"' + name + '\" value=\"' + value.id + '\"' + (n==0?'checked>':'>'));
        }
        rarios.push(value.name);
        rarios.push('</label>');
    });

    $("#" + id).empty().append(rarios.join(''));
}

/**
 * 获取radio选中值
 * @param name
 * @returns {FormUtil}
 */
FormUtil.getRadio = function(name){
    return $("input[name="+ name + "]:checked").val()
};
/**
 * 监听：赋值radio选中值给id的元素
 * @param name
 * @param id
 */
FormUtil.setValByRadio = function(name, id){
    $("input[name='" + name + "']").bind('click',function(){
        $('#' + id).val($("input[name="+ name + "]:checked").val());
    });
    return this;
};
/**
 * 根据id的值val设置勾选
 * @param id
 * @param name
 */
FormUtil.initRadioById = function(name, id){
    var val = document.getElementById(id).value;
    $("input[name='" + name + "']").each(function(){
        if(this.value == val){
            this.checked = true;
        }
    });
    return this;
};

/**
 * 根据id的值val设置勾选
 * @param id
 * @param name
 */
FormUtil.initRadioByVal = function(name, val){
    $("input[name='" + name + "']").each(function(){
        if(this.value == val){
            this.checked = true;
        }
    });
    return this;
};

FormUtil.initRadios = function(json){
    $.each(json, function(i, v){
        FormUtil.initRadioByVal(i, v);
    });
}

FormUtil.getRadios2Json = function(names){
    var obj = {};
    if(names.indexOf(',') > -1){
        var arr = names.split(',');
        $.each(arr, function(i, v){
            obj[v] = FormUtil.getRadio(v);
        })
    }
    return obj;
}
/**
 * 取radio存到localStorage
 * @param names
 * @param localStorageCode
 */
FormUtil.saveRadios2Local = function(names, localStorageCode){
    CacheUtils.setLocalStorageData(FormUtil.getRadios2Json(names), localStorageCode);
}

FormUtil.initRadios4Local = function(localStorageCode){
    FormUtil.initRadios(CacheUtils.getLocalStorageData(localStorageCode));
}

/****************************** form end ***************************************/

/**
 * 设置一个遮罩层
 */
FormUtil.showOverlay = function(type){
    if(!$('#FormUtil_showOverlay')[0]){
        var obj = $('<div id=\"FormUtil_showOverlay\"></div>').appendTo('body').css({
            'position' : 'fixed',
            'top': '0',
            'left': '0',
            'width': '100%',
            'height': '100%',
            'z-index': '9002',
            'background': '#000',
            'opacity': '0.5',
            'display': 'none'
        });
    };

    switch(type){
        case 'show' : $('#FormUtil_showOverlay').css('display', 'block');
            break;
        case 'hide' : $('#FormUtil_showOverlay').css('display', 'none');
            break;
    };
};

/**
 * 验证码倒计时
 * @param id
 */
FormUtil.setInterval = function(id){
    var InterValObj; //timer变量，控制时间
    var count = 10; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    var id = "#" + id;

    function sendMessage() {
        curCount = count;
        //设置button效果，开始计时
        $(id).attr("disabled", "true");
        $(id).val(curCount + "秒后可重新发送");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $(id).removeAttr("disabled");//启用按钮
            $(id).val("重新发送验证码");
        } else {
            curCount--;
            $(id).val(curCount + "秒后可重新发送");
        }
    }

    sendMessage(id);
}

/****************************** 根据传参批量设置 bin ******************************/
/**	type:
 hide - 隐藏
 show - 显示
 readOnly - 只读
 read - 可读
 disabled - 不可用
 able - 可用
 */
FormUtil.setAbles = function (ids, type){
    if(type == 'hide'){
        setHidShow(ids,'hide');
    }else if(type == 'show'){
        setHidShow(ids,'show');
    }else if(type == 'readOnly'){
        setReadOnly(ids,'readOnly');
    }else if(type == 'read'){
        setReadOnly(ids,'read')
    }else if(type == 'disabled'){
        setAbleDis(ids,'disabled');
    }else if(type == 'able'){
        setAbleDis(ids,'able');
    }else{
        console.log('FormUtil.setAbles 参数错误,批量设置，请输入正确的类型');
    }
}

//隐藏，显示
function setHidShow(ids,type){
    var key = ids.indexOf(',');
    if(type == 'hide'){
        if(key > -1){
            var arr = ids.split(',');
            for(i=0;i<arr.length;i++){
                document.getElementById(arr[i]).style.display = 'none';
            }
        }else{
            document.getElementById(ids).style.display = 'none';
        }
    }else if(type == 'show'){
        if(key > -1){
            var arr = ids.split(',');
            for(i=0;i<arr.length;i++){
                document.getElementById(arr[i]).style.display = '';
            }
        }else{
            document.getElementById(ids).style.display = '';
        }
    }
}
//隐藏，显示
function setReadOnly(ids,type){
    var key = ids.indexOf(',');
    if(type == 'readOnly'){
        if(key > -1){
            var arr = ids.split(',');
            for(i=0;i<arr.length;i++){
                document.getElementById(arr[i]).readOnly = true;
            }
        }else{
            document.getElementById(ids).readOnly = true;
        }
    }else if(type == 'read'){
        if(key > -1){
            var arr = ids.split(',');
            for(i=0;i<arr.length;i++){
                document.getElementById(arr[i]).readOnly = false;
            }
        }else{
            document.getElementById(ids).readOnly = false;
        }
    }
}
//可用不可用
function setAbleDis(ids,type){
    var key = ids.indexOf(',');
    //普通元素
    if(type == 'disabled'){
        if(key > -1){
            var arr = ids.split(',');
            for(i=0;i<arr.length;i++){
                if(FormUtil.setAbleDisEasyUI(arr[i], 'disable')){  //esayui元素
                    document.getElementById(arr[i]).disabled = true;
                }
            }
        }else{
            if(FormUtil.setAbleDisEasyUI(ids, 'disable')){  //esayui元素
                document.getElementById(ids).disabled = true;
            }
        }
    }else if(type == 'able'){
        if(key > -1){
            var arr = ids.split(',');
            for(i=0;i<arr.length;i++){
                if(FormUtil.setAbleDisEasyUI(arr[i], 'enable')){     //esayui元素
                    document.getElementById(arr[i]).disabled = false;
                }
            }
        }else{
            if(FormUtil.setAbleDisEasyUI(ids, 'enable')) {  //esayui元素
                document.getElementById(ids).disabled = false;
            }
        }
    }
}
/****************************** 根据传参批量设置 end ******************************/

/****************************** table bin ***************************************/
/**
 * 创建tab bootstrap
 */
FormUtil.createTab = function(id, title, json){
    var rarios = [];
    rarios.push('<table class=\"table table-hover\">');
    rarios.push('<thead>');

    rarios.push('<tr>');
    for(var key in title){
        rarios.push('<th>' + title[key] + '</th>');
    }
    rarios.push('</tr>');

    $.each(json, function (i, value) {
        if(value){
            rarios.push('<tr>');
            for(var key in title){
                rarios.push('<th>' + value[key] + '</th>');
            }
            rarios.push('</tr>');
        }
    });
    rarios.push('</thead>');
    rarios.push('</table>');
    $("#" + id).empty().append(rarios.join(''));
}
/****************************** table end ***************************************/


/****************************** checkbox bin ******************************/
/**
 * 单个初始化
 * @param id
 * @param ipt_id
 */
FormUtil.initCheckbox = function (id, ipt_id){
    var iptVal = document.getElementById(ipt_id).value;
    var obj = document.getElementById(id);
    if(iptVal == obj.value){
        obj.checked = true;
    }else{
        obj.checked = false;
    }
}

//checkbox设置input
function setCheckBoxVal(id,ipt_id,checked,unchecked){
    var obj = document.getElementById(id);
    if(obj.checked){
        document.getElementById(ipt_id).value = checked;
    }else{
        document.getElementById(ipt_id).value = unchecked;
    }
}
/**
 * 获取得到的所有checkbox值
 * @param checkbox_name
 * @returns {Array}
 */
FormUtil.getChecked = function(checkbox_name) {
    var arr = [];
    var obj = document.getElementsByName(checkbox_name);
    for(var i=0; i < obj.length; i ++){
        obj[i].checked && arr.push(obj[i]["id"]);
    }
    return arr;
}

/**
 * @param checkbox_name
 * @returns 例如得到 ‘grouponActivityDto.needGroupon=1&grouponActivityDto.needGroupon=1&’
 */
FormUtil.getCheckBoxStrByName = function(checkbox_name){
    var arr = '';
    var obj = document.getElementsByName(checkbox_name);
    for(var i=0; i < obj.length; i ++){
        var val = 0;
        if(obj[i].checked){
            val = 1;
        }
        if(i < obj.length - 1){
            arr += obj[i].id + '=' + val + '&';
        }else{
            arr += obj[i].id + '=' + val;
        }
    }
    return arr;
}

/**
 * 批量赋值
 * @param Json
 */
FormUtil.vals = function(Json){
    try{
        for(var id in Json){
            var obj = $('#' + id);
            var clas = obj.attr('class');
            if(clas.indexOf('textbox') > -1){
                obj.textbox('setValue', Json[id]);
            }else if(clas.indexOf('datebox') > -1){
                obj.datebox('setValue', Json[id]);
            }else if(clas.indexOf('numberbox') > -1){
                obj.numberbox('setValue', Json[id]);
            }else if(clas.indexOf('combobox') > -1){
                obj.combobox('setValue', Json[id]);
            }else{
                obj.val(Json[id]);
            }
        }
    }catch(e){
        console.error('FormUtil.vals 方法异常：' + JSON.stringify(Json));
    }
}
/****************************** checkbox end ******************************/

/****************************** select bin ******************************/

FormUtil.getselected = function(oSelectBox){
    if(oSelectBox.type == "select-one"){
        var iChoice = oSelectBox.selectedIndex;
        return oSelectBox.options[iChoice].value;
    }else {
        var arr = [];
        for (var i = 0; i < oSelectBox.options.length; i++)
            if (oSelectBox.options[i].selected){
                arr.push(oSelectBox.options[i].value);
            }
        return arr;
    }
}

/****************************** select end ******************************/
