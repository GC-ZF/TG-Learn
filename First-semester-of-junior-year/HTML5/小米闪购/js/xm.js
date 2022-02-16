// JavaScript Document
//JS控制标签切换显示
//获取ul里的li标签
var tabs=document.getElementById("tabs").getElementsByTagName("li");
var lists=document.getElementById("lists").getElementsByTagName("ul");
//给每一个li绑定单击响应事件
for(var i=0;i<tabs.length;i++)
{
	tabs[i].onclick=showlist;
}
function showlist()
{
	for(var i=0;i<tabs.length;i++)
	{
		if(tabs[i]===this)//如果正在点击此li
		{
		tabs[i].className="active";//则赋值active
		//JS控制商品切换显示
		lists[i].className="clearfix active";
		}
		else
		{
		tabs[i].className="";//否则赋值为空
		lists[i].className="clearfix";
		}
	}
}
//JS控制菜单固定
var sck=document.getElementById("seckillNav");
window.onscroll=function()
{
  var sc=document.documentElement.scrollTop ||documet.body.scrollTop || window.pageYOffset;//解决浏览器兼容问题
  if(sc>=260)
  {
	  sck.className="seckill-nav seckill-fixed";
  }
  else
  {
	  sck.className="seckill-nav";
	  
  }
};
