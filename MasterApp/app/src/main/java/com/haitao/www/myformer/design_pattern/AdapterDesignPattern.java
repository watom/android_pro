package com.haitao.www.myformer.design_pattern;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitao.www.myformer.R;

public class AdapterDesignPattern extends BaseExpandableListAdapter {
    private Context context;

    public AdapterDesignPattern(Context context) {
        this.context = context;
    }

    int[] logos = new int[]{R.drawable.ic_cpu, R.drawable.ic_cpu, R.drawable.ic_cpu};
    private String[] groupTypes = new String[]{"创建型模式", "结构型模式", "行为型模式"};
    private String[] groupTypesDesc = new String[]{"5种", "7种", "11种"};
    private String[][] childrenTypes = new String[][]{{"单例模式", "工厂模式", "抽象工厂模式", "建造者模式", "原型模式"}, {"适配器模式", "桥接模式", "装饰模式", "组合模式", "外观模式", "享元模式", "代理模式"}, {"策略模式", "模板方法模式", "观察者模式", "迭代子模式", "责任链模式", "命令模式", "备忘录模式", "状态模式", "访问者模式", "中介者模式", "解释器模式"}};

    @Override
    public int getGroupCount() {
        return groupTypes.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childrenTypes[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupTypes[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenTypes[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_online_grounp, null);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.imagePic.setImageResource(logos[groupPosition]);
        groupHolder.tvTitle.setText(getGroup(groupPosition).toString());
        groupHolder.tvDesc.setText(groupTypesDesc[groupPosition].toString());

        if (isExpanded) {
            groupHolder.imageArrow.setImageResource(R.drawable.arrow_up_01);
        } else {
            groupHolder.imageArrow.setImageResource(R.drawable.arrow_down_01);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildrenHolder childrenHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_online_children, null);
            childrenHolder = new ChildrenHolder(convertView);
            convertView.setTag(childrenHolder);
        } else {
            childrenHolder = (ChildrenHolder) convertView.getTag();
        }
        childrenHolder.tvNum.setText(Long.toString(getChildId(groupPosition, childPosition)+1));
        childrenHolder.tvContent.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        private ImageView imagePic;
        private TextView tvTitle;
        private TextView tvDesc;
        private ImageView imageArrow;

        public GroupHolder(View content) {
            imagePic = (ImageView) content.findViewById(R.id.image_pic);
            tvTitle = (TextView) content.findViewById(R.id.tv_title);
            tvDesc = (TextView) content.findViewById(R.id.tv_name);
            imageArrow = (ImageView) content.findViewById(R.id.image_arrow);
        }
    }

    class ChildrenHolder {
        private TextView tvContent;
        private TextView tvNum;

        public ChildrenHolder(View content) {
            tvNum = (TextView) content.findViewById(R.id.tv_num);
            tvContent = (TextView) content.findViewById(R.id.tv_content);
        }
    }
}
