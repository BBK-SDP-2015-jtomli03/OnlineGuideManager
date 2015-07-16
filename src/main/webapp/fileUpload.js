/**
 * Script to handle the post request of the xml form to the server,
 *
 * and handle the return response.
 **/

http://www.codingpedia.org/ama/how-to-add-cors-support-on-the-server-side-in-java-with-jersey/
//
//$(document).ready(function() {
//    // Get the form.
//    var form = $('#xmlUploader');
//
//    // Get the file.
//    var file = document.getElementById('xmlFile').getUserData("xmlFile");
//
//    // Event listener for the xml form.
//    $(form).submit(function(event) {
//        // Stop the browser from submitting the form.
//        event.preventDefault();
//
//        var data = new FormData();
//        data.append('xmlFile', file);
//
//        var xmlhttp;
//        if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
//            xmlhttp = new XMLHttpRequest();
//        }
//        else{// code for IE6, IE5
//            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//        }
//
//        xmlhttp.open("POST","http://localhost:8080/programmes/bond", false);
//        xmlhttp.setRequestHeader("Content-type", "multipart/form-data");
//        //xmlhttp.processData = false;
//        xmlhttp.send(data);
//
//        // Set up a handler for when the request finishes.
//        xmlhttp.onreadystatechange = function () {
//            if (xmlhttp.readyState == 4 && xmlhttp.status === 200) {
//                // File(s) uploaded.
//                document.getElementById("uploadMessage").innerHTML= "File upload successful!";
//            } else {
//                alert('An error occurred!' + xmlhttp.status);
//            }
//        };
//        return false;
//    });
//});

//function sendXml()   {
//    //var file = document.getElementById('xmlFile').getUserData("xmlFile");
//    var file = document.getElementById('xmlFile').getParameter('upload');
//
//    // console.log($.isXMLDoc(xmlData));
//    $.ajax({
//        url: 'http://localhost:8080/programmes/bond',
//        processData: false,
//        type: "POST",  // type should be POST
//        data: file, // send the string directly
//        success: function(response){
//            alert(response);
//        },
//        error: function(response) {
//            alert(response);
//        }
//    });
//}

// Variable to store your files

var files;

// Add events
$('input[type=file]').on('change', prepareUpload);

// Grab the files and set them to our variable
function prepareUpload(event)
{
    files = event.target.files;
}

$('form').on('submit', uploadFiles);

// Catch the form submit and upload the files
function uploadFiles(event)
{
    event.stopPropagation(); // Stop stuff happening
    event.preventDefault(); // Totally stop stuff happening

    // Create a formdata object and add the files
    var data = new FormData();
    $.each(files, function(key, value)
    {
        data.append(key, value);
    });

    $.ajax({
        url: 'http://localhost:8080/programmes/bond',
        type: 'POST',
        data: data,
        cache: false,
        dataType: 'multipart/form-data',
        processData: false, // Don't process the files
        contentType: false, // Set content type to false as jQuery will tell the server its a query string request
        success: function(response)
        {
            document.getElementById("uploadMessage").innerHTML= "File upload successful!";
        },
        error: function(response)
        {
            // Handle errors here
            console.log('ERRORS: ' + textStatus);
        }
    });
}
