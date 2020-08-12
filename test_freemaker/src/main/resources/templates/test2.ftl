<#if token == "1234">
  显示数字 <br/>
</#if>

<#if num gt 60 >
    num大于60 <br/>
</#if>

<#if token?? >
  token不为空  <br/>
</#if>

<#if !token2?? >
token2为空 <br/>
</#if>

${token3!'3的默认值'} <br/>