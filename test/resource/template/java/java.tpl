<#list root.fileComment as com>
${com}
</#list>
package ${root.packageName};

<#list root.importList as imp>
import ${imp};
</#list>

<#list root.classComment as com>
${com}
</#list>
<#list root.annotationList as anno>
${anno}
</#list>
public <#if !root.interface>class<#else>interface</#if> ${root.className} <#if root.extendsName??>extends ${root.extendsName} </#if><#list root.interfaceList as inter><#if inter_index == 0>implements</#if> ${inter}<#if inter_has_next>,</#if></#list> {
    <#list root.propertyList as prop>

    <#list prop.comment as com>
    ${com}
    </#list>
    <#list prop.annotationList as anno>
    ${anno}
    </#list>
    ${prop.publicType} ${prop.type} ${prop.name};
    </#list>

    <#list root.methodList as meth>

    <#list meth.comment as com>
    ${com}
    </#list>
    <#list meth.annotationList as anno>
    ${anno}
    </#list>
    <#if !root.interface>${meth.publicType} </#if>${meth.returnType} ${meth.name}(<#list meth.parameterList as param>${param}<#if param_has_next>, </#if></#list>)<#list meth.throwsList as throw><#if throw_index == 0> throws </#if>${throw}<#if throw_has_next>, </#if></#list><#if !meth.abstract>{
        <#list meth.content as row>
        ${row}
        </#list>
    }
    <#else>;
    </#if>
    </#list>

}