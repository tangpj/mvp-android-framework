package com.mvp.framework.module.test.bean;

import java.util.List;

/**
 * @ClassName: NuoMiCategoryBean
 * @author create by Tang
 * @date date 16/10/12 下午5:41
 * @Description: 糯米分类实体类
 */

public class NuoMiCategoryBean {

    //一级分类id
    public String cat_id;

    //一级分类名称
    public String cat_name;

    //二级分类列表
    public List<Subcategories> subcategories;

    public class Subcategories{

        //二级分类id
        public String subcat_id;

        //二级分类名称
        public String subcat_name;

    }

}
