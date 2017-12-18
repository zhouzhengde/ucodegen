<!--****************************************
   @description     结果映射Mapper ${root.module.entity}
   @author          ${config.author}
*****************************************-->
<resultMap id="BaseResultMap" type="${root.typeName}">
<#list root.fieldList as field>
    <#if field.comment??><!-- ${field.comment} --></#if>
    <#if field.PK>
    <id column="${field.name}" property="${field.property}" />
    <#else>
    <result column="${field.name}" property="${field.property}" />
    </#if>
</#list>
</resultMap>

<!--****************************************
   @description     返回结果列 ${root.module.entity}
   @author          ${config.author}
*****************************************-->
<sql id="BaseColumnList">
<#list root.fieldList as field>
    mt.${field.name}<#if field_has_next>,</#if>
</#list>
</sql>

<!--****************************************
   @description     根据主键查询 ${root.module.entity}
   @author          ${config.author}
   @parameter       pk
*****************************************-->
<select id="findByPK" resultMap="BaseResultMap">
    SELECT
    <include refid="BaseColumnList" />
    FROM ${root.schemaName} mt
    WHERE <#if root.useLogicDel == true>mt.is_del = 0</#if>
    <#list root.PKList as field>
    <#if field_index == 0>
    <#if root.useLogicDel == true>AND </#if>${field.name} = ${field.propertyValue}
    </#if>
    <#if field_index gt 0>
    AND ${field.name} = ${field.propertyValue}
    </#if>
    </#list>
</select>