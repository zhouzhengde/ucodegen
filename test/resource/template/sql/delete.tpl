<!--****************************************
   @description     逻辑删除 ${root.module.entity}
   @author          ${config.author}
   @parameter       ${root.typeName}
*****************************************-->
<#if root.useLogicDel == true>
<update id="deleteByPK" parameterType="${root.typeName}">
    UPDATE ${root.schemaName} is_del = 1
    <#list root.PKList as field>
    <#if field_index == 0>
    WHERE ${field.name} = ${field.propertyValue}
    </#if>
    <#if field_index gt 0>
    AND ${field.name} = ${field.propertyValue}
    </#if>
    </#list>
</update>
<#else>
<delete id="delete"  parameterType="${root.typeName}">
    DELETE FROM ${root.schemaName}
    <#list root.PKList as field>
    <#if field_index == 0>
    WHERE ${field.name} = ${field.propertyValue}
    </#if>
    <#if field_index gt 0>
    AND ${field.name} = ${field.propertyValue}
    </#if>
    </#list>
</delete>
</#if>