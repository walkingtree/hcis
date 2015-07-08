
var Configuration = function(){
this.name = "appuser";
this.path = "/HamsaUI";
this.remembermename ="rememberMe";
this.loginLocation = getBaseURL();

}
configuration = new Configuration();

function getBaseURL() {
    var url = location.href;  // entire url including querystring - also: window.location.href;
    var baseURL = url.substring(0, url.indexOf('/', 14));


//    if (baseURL.indexOf('http://localhost') != -1) {
        // Base Url for localhost
        var url = location.href;  // window.location.href;
        var pathname = location.pathname;  // window.location.pathname;
        var index1 = url.indexOf(pathname);
        var index2 = url.indexOf("/", index1 + 1);
        var baseLocalUrl = url.substr(0, index2);
//location.href.substring(0,location.href.lastIndexOf("/")+1);
        return baseLocalUrl + "/";
//    }
//    else {
        // Root Url for domain name
//        return baseURL + "/";
//    }

}