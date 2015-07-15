/**
 * Script to handle the post request of the xml form to the server,
 *
 * and handle the return response.
 **/

$(function() {
    // Get the form.
    var form = $('#xmlUploader');

    // Get the file.
    var file = document.getElementById('xmlFile').getUserData("xmlFile");

    // Event listener for the xml form.
    $(form).submit(function(event) {
        // Stop the browser from submitting the form.
        event.preventDefault();

        var xmlhttp;
        if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else{// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }

        xmlhttp.open("POST","http://localhost:8080/programmes/bond", true);
        xmlhttp.setRequestHeader("Content-type", "multipart/form-data");
        xmlhttp.send(file);

        // Set up a handler for when the request finishes.
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status === 200) {
                // File(s) uploaded.
                document.getElementById("uploadMessage").innerHTML= "File upload successful!";
            } else {
                alert('An error occurred!');
            }
        };
        return false;
    });
});
