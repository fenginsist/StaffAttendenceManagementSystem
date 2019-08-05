package com.feng.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 以下都是导航栏属性
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageInfoBean {

    private int pageNum;    //当前页码                      前传  已知
    private int pageRecord; //每页显示的记录的条数           前传 已知 自己定义
    private int startIndex; //每页显示的第一条记录的编号

    private int totalRecord;//一共需要显示的记录的总条数
    private List list;      //要显示的记录的集合             前传 已知

    private int totalPage;  //导航栏的总页数                   判断      与总页数有关系
    private int firstPageNum; //导航栏的开始页数
    private int lastPageNum;  //导航栏的结束页数


/**
 * 根据要显示的
 *          记录的总条数、
 *          每页显示的记录的条数、     自己
 *          当前页码
 *          计算导航栏的总页数、
 *          开始页数和结束页数，
 *          以及 当前页的第一条记录编号
 * 可以通过构造方法计算并赋值
 *
 *
 *
 * @param pageNum
 * @param pageRecord
 * @param totalRecord
 */
    public PageInfoBean(int pageNum, int pageRecord, int totalRecord) {
        super();
        this.pageNum = pageNum;
        this.pageRecord = pageRecord;
        this.totalRecord = totalRecord;


        //计算导航栏的总页数
        if(totalRecord%pageRecord==0){
            this.totalPage=totalRecord/pageRecord;
        }else{
            this.totalPage=totalRecord/pageRecord+1;
        }


        //默认  设置导航栏中总共显示7页，则导航栏的开始页数和结束页数初始化为：
        this.firstPageNum=1;
        this.lastPageNum=7;


        //计算导航栏的  开始页数
        if(totalPage<=7){  //如果记录总共需要显示的页数小于7页，那么，导航栏的第一页为初始值1即可，而最后一页为总共需要显示的页数。
            this.lastPageNum=totalPage;

        }else{    //如果记录总共需要显示的页数大于7页，又分为以下情况：

            //1.假设当前页定位在第4页，那么导航栏中的开始页和结束页：
            //  还需要对这两个  进行判断， firstPageNum 小于0 时，和 lastPageNum 大于totalPage时
            this.firstPageNum=pageNum-3;
            this.lastPageNum=pageNum+3;


            //2.当前页处于第4页之前（当前页-1<0）时，这时，要从第1页开始显示,因为无法从负数进行显示
            if(pageNum-3<=0){
                this.firstPageNum=1;
                this.lastPageNum=7;
            }


            //3. 当前页是倒数最后一页时，那么导航栏最后一页应该为totalPage，然后往前显示7个
            if(lastPageNum>totalPage){
                this.lastPageNum=totalPage;
                this.firstPageNum=totalPage-6;
            }

        }

        //计算  当前页的  第一条记录编号             这个时找到的规律，1-7  1页，8-14  2页
        this.startIndex=(pageNum-1)*pageRecord+1;


    }






}
