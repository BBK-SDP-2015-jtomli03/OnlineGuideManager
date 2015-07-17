<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sky File Uploader</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="main.css">
</head>
<body>

<div class="topBanner">
    <img src="img/sky_logo.png" alt="sky logo">
    <h1>Document Uploader</h1>
</div>

<form id="xmlUploader" action="http://localhost:8080/programmes/bond" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>Upload A File For The 007 Channel</legend>
        <div class="upload">
            <label>Please select a file to upload: <br>
                <input type="file" class="input"  name="xmlFile"  accept="text/xml" required>
            </label>
            <input type="submit" class="uploadButton" id="uploadfile" value="Upload File">
        </div>
    </fieldset>
</form>

<footer>
    <p>Produced by Jo Tomlinson</p>
</footer>

</body>
</html>
