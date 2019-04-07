<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<title>我要出售</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="layer/layer.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/upload_goods.css" />
	</head>
	<style type="text/css">
        html {
        	height: 100%;
        }
        h3 {
        	text-align:center;
        }
        body {
        	padding: 0;
        	margin: 0;
        	list-style:none;
        	text-decoration:none;
        	width: 100%;
        	background: url(img/goodsupload-bj.jpg) no-repeat;
        	background-size: 100% 100%;
        	position: absolute;
        }
        .bj {
        	border-radius: 8px;
        	width: 1000px;
        	height: 570px;
        	border-top: 1px solid transparent;
        	margin:0 auto;
        	margin-top: 30px;
        	background-color: rgba(128,128,128,0.5);
        }
        .container {
        	width:86%;
        	position:relative;
        	margin:0 auto;
        }
        .help-block {
         color:black;
        }
        button {
         width:100%;
         background-color: green;
        }
        .img {
        	width: 100px;
        	height: 100px;
        	position: absolute;
        	left: 300px;
        	top: 421px;
        	display: none;
        }
        .form-group {
        	margin-top: -5px;
        }
        </style>
	<body>
	<div class="bj">
		<div class="container">
			<ul class="nav nav-pills" style="height:60px">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true" style="font-size:20px;margin-top:8px;"><a href="index.s" style="color: black!important;margin:0;">返回</a></span>
             	<h3 style="margin-top:0px">商品出售</h3>
    		</ul>
			<div class="bs-example" data-example-id="basic-forms">
	<form action="UploadGoodsServlet" method="post" enctype="multipart/form-data" onsubmit="return validate();">
		<div class="form-group">
			<label for="exampleInputEmail1">商品类别</label>
			<input type="text" class="form-control" id="exampleInputEmail1" placeholder="商品类别" name="goodsClass">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">请输入商品名称</label>
			<input type="text" class="form-control" id="exampleInputEmail1" placeholder="商品名称" name="goodsName">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">预售价格（元）</label>
			<input type="number" class="form-control" id="exampleInputPassword1" placeholder="￥****" name="goodsPrice">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">库存</label>
			<input type="number" class="form-control" id="exampleInputPassword1" value="10" name="goodsTotal">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">请输入商品描述</label>
			<textarea class="form-control" rows="2" name="goodsDesc"></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputFile">上传商品图片</label>
			<input type="file" id="exampleInputFile" name="file1" class="imgInput">
			<p class="help-block">文件大小限制（2M）</p>
		</div>
		<div>
			<img src="" class="img" id="img">
		</div>
		<button type="submit" class="btn btn-default" style="background-color:green; color:white;">提交</button>
	</form>
</div>
		</div>
		</div>
	</body>
	<script type="text/javascript">
		$(".imgInput").change(function(){
			document.getElementById('img').style.display="block";
    		$(".img").attr("src",URL.createObjectURL($(this)[0].files[0]));
		});
		
	 	function validate() {
		var goodsClass = document.getElementsByName("goodsClass");
		var goodsName = document.getElementsByName("goodsName");
		var goodsPrice = document.getElementsByName("goodsPrice");
		var goodsDesc = document.getElementsByName("goodsDesc");
		var fileImg = document.getElementsByName("file1");
		
		if(goodsClass[0].value=="") {
			layer.alert("商品名称不能为空");
			goodsClass[0].focus();
			return false;
		}
		if(goodsName[0].value=="") {
			layer.alert("商品名称不能为空");
			goodsName[0].focus();
			return false;
		}
		if(goodsPrice[0].value=="") {
			layer.alert("商品价格不能为空");
			goodsPrice[0].focus();
			return false;
		}
		if(goodsDesc[0].value=="") {
			layer.alert("请添加商品描述");
			goodsDesc[0].focus();
			return false;
		}
		if(fileImg[0].value=="") {
			layer.alert("请选择一张商品图片");
			fileImg[0].focus();
			return false;
		}
		return true;
	}
	</script>
</html>