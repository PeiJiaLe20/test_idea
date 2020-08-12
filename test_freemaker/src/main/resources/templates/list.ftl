<table border="1">
    <#list AllTeacher as teacher>
    <tr >
        <td>${teacher_index + 1}</td>
        <td>${teacher.name}</td>
        <td>${teacher.age}</td>
        <td>${teacher.marry}</td>
    </tr>
    </#list>
</table>
<#list AllTeacher as teacher>
    ${teacher_index + 1} -- ${teacher.name} -- ${teacher.age} -- ${teacher.marry} <br/>
</#list>
