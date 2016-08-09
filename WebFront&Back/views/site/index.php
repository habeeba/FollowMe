<?php
/* @var $this yii\web\View */
$session = Yii::$app->session;
$session->open ();
?>

<!DOCTYPE html>
<html>
<head>
	<title>Follow Me</title>
	<meta charset="UTF-8">
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body>
	<!-- WRAPPER STARTS HERE-->
	<div class='wrapper'>
		<header>
			<!-- HEADER STARTS HERE-->
			<div class='container titles yomer-text-center'>
				<h1 data-scroll-reveal="wait 0.25s, then enter top and move 40px over 1s">Welcome To Follow Me</h1>
				<center>
				<img src="img/loogoo.png" style="width: 170px;">
				</center>
			</div>
		</header>
		<!-- /HEADER ENDS HERE-->
		<!-- ABOUT US STARTS HERE-->
		
		
		<section class='aboutus' id='about'>
			<br><br><br>
				<div class="check">
					
						<form style="float: left; margin-left: 300px;" action="index.php?r=login" method="post">
							<button class="mybutton" type="submit"> <img style="width: 120px;" src="img/staff2.png"></button><h1>Staff</h1>
							<input type="hidden" name="userType" value="staff" /> 
							<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" />
						</form>
					
					
						<form action="index.php?r=login" method="post">
							<button class="mybutton" type="submit"> <img style="width: 120px;" src="img/student.png"></button><h1>Student</h1> 
							<input type="hidden" name="userType" value="student" /> 
							<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" />
						</form>
					
				</div>
				<br>
		</section>
	<?php 
	for($i = 0; $i < 10; $i++)
	{?>
		<div style="background: url('img/background.png') repeat center ; "><br></div>
	<?php }
	?>	


	</div>
	<!-- /WRAPPER ENDS HERE DESIGNED BY VIJAYAN PP-->
	<script type='text/javascript' src='js/jquery.js'></script>
	<script type='text/javascript' src='js/bootstrap.min.js'></script>
	<script type='text/javascript' src='js/scrollReveal.js'></script>
	<script type='text/javascript'>
jQuery(document).ready(function(){  
				$(".top").click(function(){
					$("html, body").animate({ scrollTop: 0 }, "slow");
					return false;
				});
				$(".btMenu").click(function(){
			
					if($(".menu").children(".menuItem").css("display") == "none"){
						$(".menu").children(".menuItem").slideDown();
					}
					else{
						$(".menu").children(".menuItem").slideUp();
					}
				});
				$(window).resize(function(){
					if($(window).innerWidth() > 900){
						$(".menu").children(".menuItem").css("display", "block");
					}
					else{
						$(".menu").children(".menuItem").css("display", "none");
					}
				});
				
				(function(k){'use strict';k(['jquery'],function($){var j=$.scrollTo=function(a,b,c){return $(window).scrollTo(a,b,c)};j.defaults={axis:'xy',duration:parseFloat($.fn.jquery)>=1.3?0:1,limit:!0};j.window=function(a){return $(window)._scrollable()};$.fn._scrollable=function(){return this.map(function(){var a=this,isWin=!a.nodeName||$.inArray(a.nodeName.toLowerCase(),['iframe','#document','html','body'])!=-1;if(!isWin)return a;var b=(a.contentWindow||a).document||a.ownerDocument||a;return/webkit/i.test(navigator.userAgent)||b.compatMode=='BackCompat'?b.body:b.documentElement})};$.fn.scrollTo=function(f,g,h){if(typeof g=='object'){h=g;g=0}if(typeof h=='function')h={onAfter:h};if(f=='max')f=9e9;h=$.extend({},j.defaults,h);g=g||h.duration;h.queue=h.queue&&h.axis.length>1;if(h.queue)g/=2;h.offset=both(h.offset);h.over=both(h.over);return this._scrollable().each(function(){if(f==null)return;var d=this,$elem=$(d),targ=f,toff,attr={},win=$elem.is('html,body');switch(typeof targ){case'number':case'string':if(/^([+-]=?)?\d+(\.\d+)?(px|%)?$/.test(targ)){targ=both(targ);break}targ=win?$(targ):$(targ,this);if(!targ.length)return;case'object':if(targ.is||targ.style)toff=(targ=$(targ)).offset()}var e=$.isFunction(h.offset)&&h.offset(d,targ)||h.offset;$.each(h.axis.split(''),function(i,a){var b=a=='x'?'Left':'Top',pos=b.toLowerCase(),key='scroll'+b,old=d[key],max=j.max(d,a);if(toff){attr[key]=toff[pos]+(win?0:old-$elem.offset()[pos]);if(h.margin){attr[key]-=parseInt(targ.css('margin'+b))||0;attr[key]-=parseInt(targ.css('border'+b+'Width'))||0}attr[key]+=e[pos]||0;if(h.over[pos])attr[key]+=targ[a=='x'?'width':'height']()*h.over[pos]}else{var c=targ[pos];attr[key]=c.slice&&c.slice(-1)=='%'?parseFloat(c)/100*max:c}if(h.limit&&/^\d+$/.test(attr[key]))attr[key]=attr[key]<=0?0:Math.min(attr[key],max);if(!i&&h.queue){if(old!=attr[key])animate(h.onAfterFirst);delete attr[key]}});animate(h.onAfter);function animate(a){$elem.animate(attr,g,h.easing,a&&function(){a.call(this,targ,h)})}}).end()};j.max=function(a,b){var c=b=='x'?'Width':'Height',scroll='scroll'+c;if(!$(a).is('html,body'))return a[scroll]-$(a)[c.toLowerCase()]();var d='client'+c,html=a.ownerDocument.documentElement,body=a.ownerDocument.body;return Math.max(html[scroll],body[scroll])-Math.min(html[d],body[d])};function both(a){return $.isFunction(a)||typeof a=='object'?a:{top:a,left:a}}return j})}(typeof define==='function'&&define.amd?define:function(a,b){if(typeof module!=='undefined'&&module.exports){module.exports=b(require('jquery'))}else{b(jQuery)}}));
				
				$(".menu").children("li:nth-child(3)").click(function(){
				$("body").scrollTo("#about", 600);
                				
				});
				
				$(".menu").children("li:nth-child(4)").click(function(){
				$("body").scrollTo("#mission", 600);
				});
				
				$(".menu").children("li:nth-child(5)").click(function(){
				
				$("body").scrollTo("#whyus", 600);
				});
				
				$(".menu").children("li:nth-child(6)").click(function(){
				$("body").scrollTo("#whychose", 600);
				});
				
			});

			(function($) {
      window.scrollReveal = new scrollReveal();
    })();
			
</script>
</body>
</html>