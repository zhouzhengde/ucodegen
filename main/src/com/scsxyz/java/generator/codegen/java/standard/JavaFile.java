package com.scsxyz.java.generator.codegen.java.standard;

import com.scsxyz.java.generator.util.DateUtils;

import java.util.*;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public class JavaFile {

    private String author;

    private boolean isInterface;

    public List<String> fileComment = new ArrayList<>();

    public String packageName;

    public List<String> classComment = new ArrayList<>();

    public String className;

    public String extendsName;

    public Set<String> interfaceList = new HashSet<>();

    public Set<String> importList = new TreeSet<>();

    public List<Property> propertyList = new ArrayList<>();

    public List<Method> methodList = new ArrayList<>();

    public List<String> annotationList = new ArrayList<>();

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public JavaFile(List<String> fileComment) {
        this.fileComment = fileComment;
    }

    public JavaFile() {
    }

    public List<String> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<String> annotationList) {
        this.annotationList = annotationList;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public List<String> getFileComment() {
        return fileComment;
    }

    public void setFileComment(List<String> fileComment) {
        this.fileComment = fileComment;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List getClassComment() {
        return classComment;
    }

    public void setClassComment(List classComment) {
        this.classComment = classComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getExtendsName() {
        return extendsName;
    }

    public void setExtendsName(String extendsName) {
        this.extendsName = extendsName;
    }

    public Set<String> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(Set<String> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public Set<String> getImportList() {
        return importList;
    }

    public void setImportList(Set<String> importList) {
        this.importList = importList;
    }


    public List<Method> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<Method> methodList) {
        this.methodList = methodList;
    }

    public static class Property {

        private List<String> comment = new ArrayList<>();
        ;

        private String publicType = "private";

        private String type;

        private String name;

        private boolean useGet;

        private boolean useSet;

        private Set<String> annotationList = new HashSet<>();

        public List<String> getComment() {
            return comment;
        }

        public void setComment(List<String> comment) {
            this.comment = comment;
        }

        public String getPublicType() {
            return publicType;
        }

        public boolean isUseGet() {
            return useGet;
        }

        public void setUseGet(boolean useGet) {
            this.useGet = useGet;
        }

        public void setPublicType(String publicType) {
            this.publicType = publicType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isUseSet() {
            return useSet;
        }

        public void setUseSet(boolean useSet) {
            this.useSet = useSet;
        }

        public Set<String> getAnnotationList() {
            return annotationList;
        }

        public void setAnnotationList(Set<String> annotationList) {
            this.annotationList = annotationList;
        }
    }

    public static class Method {

        private List<String> comment = new ArrayList<>();

        private String publicType;

        private String returnType = "void";

        private String name;

        private boolean isAbstract;

        private List<String> parameterList = new ArrayList<>();

        private List<String> content = new ArrayList<>();

        private List<String> throwsList = new ArrayList<>();

        private List<String> annotationList = new ArrayList<>();

        public Method() {
        }

        /**
         * @param comment
         * @param publicType
         * @param returnType
         * @param name
         */
        public Method(List<String> comment, String publicType, String returnType, String name) {
            this.comment = comment;
            this.publicType = publicType;
            this.returnType = returnType;
            this.name = name;
        }

        /**
         * @param comment
         * @param publicType
         * @param name
         * @param parameterList
         */
        public Method(List<String> comment, String publicType, String name, List<String> parameterList) {
            this.comment = comment;
            this.publicType = publicType;
            this.name = name;
            this.parameterList = parameterList;
        }

        /**
         * @param comment
         * @param publicType
         * @param name
         */
        public Method(List<String> comment, String publicType, String name) {
            this.comment = comment;
            this.publicType = publicType;
            this.name = name;
        }

        /**
         * @param comment
         * @param publicType
         * @param returnType
         * @param name
         * @param parameterList
         */
        public Method(List<String> comment, String publicType, String returnType, String name, List<String> parameterList) {
            this.comment = comment;
            this.publicType = publicType;
            this.returnType = returnType;
            this.name = name;
            this.parameterList = parameterList;
        }

        public List<String> getAnnotationList() {
            return annotationList;
        }

        public void setAnnotationList(List<String> annotationList) {
            this.annotationList = annotationList;
        }

        public List<String> getThrowsList() {
            return throwsList;
        }

        public void setThrowsList(List<String> throwsList) {
            this.throwsList = throwsList;
        }

        public boolean isAbstract() {
            return isAbstract;
        }

        public void setAbstract(boolean anAbstract) {
            isAbstract = anAbstract;
        }

        public List<String> getComment() {
            return comment;
        }

        public void setComment(List<String> comment) {
            this.comment = comment;
        }

        public String getPublicType() {
            return publicType;
        }

        public void setPublicType(String publicType) {
            this.publicType = publicType;
        }

        public String getReturnType() {
            return returnType;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getParameterList() {
            return parameterList;
        }

        public void setParameterList(List<String> parameterList) {
            this.parameterList = parameterList;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }

    public static class Parameter {

        private List<String> annotationList = new ArrayList<>();
        private boolean useFinal;
        private String type;
        private String name;

        public Parameter() {
        }

        public Parameter(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public Parameter(boolean useFinal, String type, String name) {
            this.useFinal = useFinal;
            this.type = type;
            this.name = name;
        }

        public String getShortType() {
            if (type.contains(".")) {
                return type.substring(type.lastIndexOf(".") + 1);
            }
            return type;
        }

        public boolean isUseFinal() {
            return useFinal;
        }

        public List<String> getAnnotationList() {
            return annotationList;
        }

        public void setAnnotationList(List<String> annotationList) {
            this.annotationList = annotationList;
        }

        public void setUseFinal(boolean useFinal) {
            this.useFinal = useFinal;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            if (useFinal) {
                return "final " + type + " " + name;
            }
            return type + " " + name;
        }
    }

    public static class Comment {
        private String des;
        private String author = "ucodegen";
        private List<String> params = new ArrayList<>();
        private String returnType;
        private List<String> throwsSet = new ArrayList<>();

        public Comment(String des) {
            this.des = des;
        }

        public Comment(String des, String author, List<String> params, String returnType, List<String> throwsSet) {
            this.des = des;
            this.author = author;
            this.params = params;
            this.returnType = returnType;
            this.throwsSet = throwsSet;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }

        public String getReturnType() {
            return returnType;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }

        public List<String> getThrowsSet() {
            return throwsSet;
        }

        public void setThrowsSet(List<String> throwsSet) {
            this.throwsSet = throwsSet;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }


        public List<String> toComment() {
            List<String> rs = new ArrayList<>();
            StringBuffer sb = new StringBuffer();
            rs.add("/**");
            if (des != null) {
                rs.add(" * " + des);
            }
            rs.add(" * @author " + getAuthor());
            //rs.add(" * @date " + DateUtils.formatNow(DateUtils.Pattern.yyyy_MM_dd));
            params.forEach((item) -> {
                String[] split = item.split(" ");
                rs.add(" * @param " + split[split.length - 1]);
            });
            if (returnType != null) {
                rs.add(" * @return " + returnType);
            }
            throwsSet.forEach((item) -> {
                rs.add(" * @throws " + item);
            });
            rs.add(" */");
            return rs;
        }
    }

    public static class CodeRow {
        private String row;

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public CodeRow(String row) {
            this.row = row;
        }

        @Override
        public String toString() {
            if (row == null || row.trim() == "") {
                return "";
            }
            return row + ";";
        }
    }
}
