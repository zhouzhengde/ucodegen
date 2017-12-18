<!--****************************************
   @description     添加 ${root.module.entity}
   @author          ${config.author}
   @parameter       ${root.typeName}
*****************************************-->
<insert id="insert" parameterType="${root.typeName}">
    INSERT INTO ${root.schemaName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list root.fieldList as field>
        <if test="${field.property} != null">
            ${field.name},
        </if>
        </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list root.fieldList as field>
        <if test="${field.property} != null">
            ${field.propertyValue},
        </if>
        </#list>
    </trim>
</insert>