/*
 *display
 */
var type = {
    "1": "NET程序员",
    "2": "PHP程序员",
    "3": "Java程序员",
    "4": "C/C++程序员",
    "19": "Python程序员",
    "20": "Ruby程序员",
    "5": "JavaScript程序员",
    "6": "Flash程序员",
    "7": "Delphi程序员",
    "8": "前端开发工程师",
    "9": "项目经理",
    "10": "技术主管",
    "11": "架构师",
    "12": "技术总监",
    "13": "测试工程师",
    "14": "系统管理员",
    "15": "数据库管理员",
    "16": "售前工程师",
    "17": "手机应用开发工程师",
    "18": "其他"
};
var salary = {
    "0000001000": "1000元以下",
    "0100002000": "1000-2000元",
    "0200104000": "2001-4000元",
    "0400106000": "4001-6000元",
    "0600108000": "6001-8000元",
    "0800110000": "8001-10000元",
    "1000115000": "10001-15000元",
    "1500125000": "15000-25000元",
    "2500199999": "25000元以上"
};
var exp={
		"0":"无经验要求",
		"1":"1~3年",
		"2":"3~5年",
		"3":"5~10年"
};
var edu={
		"0":"无学历要求",
		"1":"大专",
		"2":"本科",
		"3":"研究生",
		"4":"硕士",
		"5":"博士"
};
function distype(a){
    document.write(type[a]);
}

function dissalary(a){
    document.write(salary[a]);
}

function disexp(a){
    document.write(exp[a]);
}

function disedu(a){
    document.write(edu[a]);
}
/*
 * end dis
 */


/*
 *post
*/


function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
}


//end
