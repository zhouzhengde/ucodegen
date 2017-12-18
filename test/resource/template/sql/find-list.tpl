<!--****************************************
   @description     根据属性集查询列表 ${root.module.entity}
   @author          ${config.author}
   @parameter       ${root.typeName}
*****************************************-->
<select id="find" resultMap="BaseResultMap">
    SELECT
    <include refid="BaseColumnList" />
    FROM ${root.schemaName} mt
    <where>
    <#if root.useLogicDel == true>
        mt.is_del = 0
    </#if>
    <#list root.fieldList as field>
    <#if field.name != 'is_del'>
    <#if field_index == 0>
        <if test="${field.property} != null">
            AND mt.${field.name} = ${field.propertyValue}
        </if>
    <#else>
        <if test="${field.property} != null">
            AND mt.${field.name} = ${field.propertyValue}
        </if>
    </#if>
    </#if>
    </#list>
    </where>
</select>