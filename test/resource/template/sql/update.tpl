<!--****************************************
   @description     更新 ${root.module.entity}
   @author          ${config.author}
   @parameter       ${root.typeName}
*****************************************-->
<update id="updateByPK" parameterType="${root.typeName}">
    UPDATE ${root.schemaName}
    <set>
         <#list root.fieldList as field>
        <if test="${field.property} != null">
            ${field.name} = ${field.propertyValue},
        </if>
        </#list>
    </set>
    <#list root.PKList as field>
    <#if field_index == 0>
    WHERE ${field.name} = ${field.propertyValue}
    </#if>
    <#if field_index gt 0>
    AND ${field.name} = ${field.propertyValue}
    </#if>
    </#list>
</update>