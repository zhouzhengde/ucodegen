<insert id="insert" parameterType="${root.typeName}">
    insert into b_activity
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
            #{${field.property}},
        </if>
        </#list>
    </trim>
</insert>