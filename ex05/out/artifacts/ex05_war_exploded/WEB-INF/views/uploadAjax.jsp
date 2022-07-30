<%--
  Created by IntelliJ IDEA.
  User: woong_9yo
  Date: 2022/07/29
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .uploadResult {
            width: 100%;
            background-color: gray;
        }

        .uploadResult ul {
            display: flex;
            flex-float: row;
            justify-content: center;
            align-items: center;
        }

        .uploadResult ul li {
            list-style: none;
            padding: 10px;
            align-content: center;
            text-align: center;
        }

        .uploadResult ul li img {
            width: 100px;
        }

        .uploadResult ul li span {
            color: white;
        }

        .bigPictureWrapper {
            position: absolute;
            display: none;
            justify-content: center;
            align-content: center;
            top: 0%;
            width: 100%;
            height: 100%;
            background-color: gray;
            z-index: 100;
            background: rgba(255, 255, 255, 0.5);
        }

        .bigPicture {
            position: relative;
            display: flex;
            justify-content: center;
            align-content: center;
        }

        .bigPicture img {
            width: 600px;
        }
    </style>
</head>
<body>
<h1>Upload with Ajax</h1>

<div class="uploadDiv">
    <input type="file" name="uploadFile" multiple>
</div>
<div class="uploadResult">
    <ul>

    </ul>
</div>
<div class="bigPictureWrapper">
    <div class="bigPicture"></div>
</div>
<button id="uploadBtn">Upload</button>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script>
    const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    const maxSize = 5242880;

    function checkExtension(fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            return false;
        }
        if (regex.test(fileName)) {
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }
        return true;
    }

    function showImage(fileCallPath) {
        // alert(fileCallPath);
        $(".bigPictureWrapper").css("display", "flex").show();

        $(".bigPicture")
            .html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>")
            .animate({width: '100%', height: '100%'}, 1000);
        $(".bigPictureWrapper").on("click", function (e) {
            $(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
            setTimeout(() => {
                $(this).hide();
            }, 1000);
        })
    }

    $(document).ready(function () {
        const cloneObj = $(".uploadDiv").clone();
        $("#uploadBtn").on("click", function (e) {
            const formData = new FormData();
            const inputFile = $("input[name='uploadFile']");
            const files = inputFile[0].files;

            console.log(files);

            for (let i = 0; i < files.length; i++) {
                if (!checkExtension(files[i].name, files[i].size)) {
                    return false;
                }

                formData.append("uploadFile", files[i]);
            }

            $.ajax({
                url: '/uploadAjaxAction',
                processData: false,
                contentType: false,
                data: formData,
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    console.log(result)
                    alert("Uploaded");
                    showUploadedFile(result);
                    $(".uploadDiv").html(cloneObj.html());

                }
            });
        });

        const uploadResult = $(".uploadResult ul");

        function showUploadedFile(uploadResultArr) {
            let str = "";

            $(uploadResultArr).each(function (i, obj) {
                console.log(obj)

                if (!obj.image) {
                    const fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
                    const fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
                    str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>" + "<img src='/resources/img/attach.jpeg'/>" + obj.fileName + "</a>"
                        + "<span data-file=\'" + fileCallPath + "\' data-type='file'> x </span>" + "<div></li>";
                } else {
                    const fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
                    let originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
                    originPath = originPath.replace(new RegExp(/\\/g), "/");

                    str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\"> <img src='/display?fileName=" + fileCallPath + "'></a>" + "<span data-file=\'" + fileCallPath + "\' data-type='image'> x </span>" + "</li>";
                }
            });

            uploadResult.append(str);
        }

        $(".uploadResult").on("click", "span", function (e) {
            const targetFile = $(this).data("file");
            const type = $(this).data("type");
            console.log(targetFile);

            $.ajax({
                url: '/deleteFile',
                data: {fileName: targetFile, type: type},
                dataType: 'text',
                type: 'POST',
                success: function (result) {
                    alert(result);
                }
            })
        })
    });
</script>
</body>
</html>
