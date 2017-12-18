<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${schema.module.packageName}.dao.${schema.module.entity}Mapper">

<#list root as item>
${item}

</#list>

</mapper>