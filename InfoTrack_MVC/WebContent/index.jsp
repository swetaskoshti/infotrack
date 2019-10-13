<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Company's Position</title>
<script type="text/javascript">
	//validate function to validate that the input is not empty
    function validate() {
	//	alert("inside validate")
        var search = SEOController.search.value;
        var website = SEOController.website.value;
        if (search == null || search.trim() == "") {
            alert('Please Enter String to Search.');
            SEOController.search.focus();
            return false; // cancel submission
        } 
        else if ( website == null || website.trim() == ""){
        	alert('Please Enter Website to Search.');
            SEOController.website.focus();
            return false; // cancel submission
        }
        else if ( website != null || website.trim() != ""){
        	var returnStatement = ValidURL();
        	if(!returnStatement){
         	   SEOController.website.focus();
         	   return false; // cancel submission
        	}
        }
        else {
            document.SEOController.submit(); // allow submit
        }
    }
</script>
<script>
	function ValidURL() {
		var url = SEOController.website.value;
		var regx = new RegExp("^[www]\.[a-z]\.[a-z]\.*$", "i");
		//var regx = "^[www]\.[a-z]\.[a-z]\.*$";
		
		//var regx = "/^(www\.)+[a-zA-Z0-9\.\-\_]+(\.[a-zA-Z]{2,3})+(\/[a-zA-Z0-9\_\-\s\.\/\?\%\#\&\=]*)?$/";
        
		if (!regx.test(url)) { 
		    alert("Please Enter Valid URL");
		    SEOController.website.focus();
		    return false;
		}
		else{
			return true;
		}
	}
</script>
</head>
<body>
 <form action="SEOController" method ="POST" name="SEOController" onSubmit="return validate() ">
 <h2>Search your Company's Position</h2>
 <table  border = "1">
<tr> 
<td width="30%">Enter String to Search : </td>
<td width="70%"><input type="text" name="search" size="50"></td>
</tr>
<tr>
<td width="30%"> Enter your Company's Web site : </td>
<td width="70%"><input type="text" align="center" class="input" placeholder="www.google.com.au"  name="website" size="50"   autocomplete="off" ></td>
</tr>
 <tr>
 <td  colspan="2" align="center"><input type="submit" value="submit" ></td>
 </tr>
</table>
 </form>
</body>
</html>