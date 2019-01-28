<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<html lang="en">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
        <div class="col-lg-7">
           <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
     <h1 style="color:white;">${msg}</h1>
    </div>
  
  </div>
</nav>
           
                  <form action="sendMail" enctype="multipart/form-data"  method="post">
            <div class="form-group">
            <input type="text" placeholder="To" class="form-control" name="to">
            </div>
            <div class="form-group">
            <input type="text" placeholder="Subject" class="form-control" name="sub">
            </div>
            <input type="hidden" id="num" class="form-control" name="num" >
             <div class="form-group">
    <label for="exampleFormControlTextarea1">Message</label>
    <textarea class="form-control rounded-0" id="exampleFormControlTextarea1" rows="10" name="msg"></textarea>
</div>
<div class="form-groump">
		<label>Upload file</label>
		<input type="file" name="file" id="upload_file1" class="form-control"/>
	
	</div>
	<div id="moreImageUpload" class="form-inline"></div>
	<div class="clear"></div>
	<div id="moreImageUploadLink" style="display:none;margin-left: 10px;">
		
	</div>
 <div class="form-group">
 <div class="form-inline">
            <input type="submit" value="send"  class="btn btn-primary">
           <a href="javascript:void(0);" id="attachMore"><span class="glyphicon glyphicon-paperclip"></span></a> 
           
            </div>
            </div>
             </form>
             </div>
            </div>
        </div>
    </div>
<script >
     function isNumber(evt) {
        var iKeyCode = (evt.which) ? evt.which : evt.keyCode
        if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
            return false;

        return true;
    }  
     $(document).ready(function() {
 		$("input[id^='upload_file']").each(function() {
 			var id = parseInt(this.id.replace("upload_file", ""));
 			$("#upload_file" + id).change(function() {
 				if ($("#upload_file" + id).val() != "") {
 					$("#moreImageUploadLink").show();
 				}
 			});
 		});
 		
 	});
 	
	$(document).ready(function() {
 		
 		var upload_number = 2;
 		$('#attachMore').click(function() {
 			//add more file
 			
 			var moreUploadTag = '';
 			moreUploadTag += '<div class="form-inline"><label for="upload_file"' + upload_number + '>Upload File ' + upload_number + '</label>';
 			moreUploadTag += '<input type="file" id="upload_file ' + upload_number + '" name="file"/>';
 			moreUploadTag += ' <a href="javascript:del_file(' + upload_number + ')" id="del" style="cursor:pointer;" onclick="return confirm("Are you really want to delete ?")"><span class="glyphicon glyphicon-remove"> </span>'  + '</a></div>';
 			$('<dl id="delete_file' + upload_number + '">' + moreUploadTag + '</dl>').fadeIn('slow').appendTo('#moreImageUpload');
 			
 			upload_number++;
 			$('#del').click(function(){
 				upload_number--;
 			})
 			
 		});
 		$('#num').val(upload_number)
 		console.log(upload_number)
 		//;
 		$('#del1').click(function(){
 			upload_number=1;
				$('#upload_file').hide();
				$('#del1').hide();
			})
			
 		
 	});
 	
 	function del_file(eleId) {
 		var ele = document.getElementById("delete_file" + eleId);
 		ele.parentNode.removeChild(ele);
 	}
</script>


</body>
</html>