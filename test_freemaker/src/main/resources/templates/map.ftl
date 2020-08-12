<#-- 一个数据 -->
${allUser.user1.name} <br/>
${allUser['user1'].name} <br/>

<#-- 遍历一组 -->
<#list allUser?keys as k>
    ${k} -- ${allUser[k].name} -- ${allUser[k].age} -- ${allUser[k].marry} <br/>
</#list>

