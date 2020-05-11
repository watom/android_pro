package com.haitao.www.myformer.utils;

import android.util.ArrayMap;

public class EmotionUtils {
    /**
     * 表情类型标志符
     */
    public static final int EMOTION_CLASSIC_TYPE=0x0001;//经典表情
    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static ArrayMap<String, Integer> EMPTY_MAP;
    public static ArrayMap<String, Integer> EMOTION_CLASSIC_MAP;
    static {
        EMPTY_MAP = new ArrayMap<>();
        EMOTION_CLASSIC_MAP = new ArrayMap<>();
//        EMOTION_CLASSIC_MAP.put("[呵呵]", R.mipmap.hehe);
//        EMOTION_CLASSIC_MAP.put("[嘻嘻]", R.mipmap.xixi);
//        EMOTION_CLASSIC_MAP.put("[哈哈]", R.mipmap.haha);
//        EMOTION_CLASSIC_MAP.put("[爱你]", R.mipmap.aini);
//        EMOTION_CLASSIC_MAP.put("[吃惊]", R.mipmap.jingxia);
//        EMOTION_CLASSIC_MAP.put("[晕]", R.mipmap.gundan);
//        EMOTION_CLASSIC_MAP.put("[泪]", R.mipmap.liulei);
//        EMOTION_CLASSIC_MAP.put("[馋嘴]", R.mipmap.haochi);
//        EMOTION_CLASSIC_MAP.put("[抓狂]", R.mipmap.niuqi);
//        EMOTION_CLASSIC_MAP.put("[哼]", R.mipmap.buxie);
//        EMOTION_CLASSIC_MAP.put("[可爱]", R.mipmap.haixiu);
//        EMOTION_CLASSIC_MAP.put("[怒]", R.mipmap.nu);
//        EMOTION_CLASSIC_MAP.put("[愤怒]", R.mipmap.numa);
//        EMOTION_CLASSIC_MAP.put("[害羞]", R.mipmap.haixiu);
//        EMOTION_CLASSIC_MAP.put("[睡觉]", R.mipmap.shuijiao);
//        EMOTION_CLASSIC_MAP.put("[钱]", R.mipmap.caimi);
//        EMOTION_CLASSIC_MAP.put("[笑cry]", R.mipmap.xiaoku);
//        EMOTION_CLASSIC_MAP.put("[惬意]", R.mipmap.qieyi);
//        EMOTION_CLASSIC_MAP.put("[失望]", R.mipmap.shiwang);
//        EMOTION_CLASSIC_MAP.put("[扎实]", R.mipmap.zhashi);
//        EMOTION_CLASSIC_MAP.put("[衰]", R.mipmap.nanguo);
//        EMOTION_CLASSIC_MAP.put("[闭嘴]", R.mipmap.bizui);
//        EMOTION_CLASSIC_MAP.put("[鄙视]", R.mipmap.bishi);
//        EMOTION_CLASSIC_MAP.put("[花心]", R.mipmap.xindong);
//        EMOTION_CLASSIC_MAP.put("[耍]", R.mipmap.yiqiwan);
//        EMOTION_CLASSIC_MAP.put("[悲伤]", R.mipmap.nanshou);
//        EMOTION_CLASSIC_MAP.put("[思考]", R.mipmap.zhiyi);
//        EMOTION_CLASSIC_MAP.put("[发烧]", R.mipmap.fashao);
//        EMOTION_CLASSIC_MAP.put("[亲亲]", R.mipmap.qinqin);
//        EMOTION_CLASSIC_MAP.put("[怒骂]", R.mipmap.numa);
//        EMOTION_CLASSIC_MAP.put("[太开心]", R.mipmap.haokaixin);
//        EMOTION_CLASSIC_MAP.put("[懒得理你]", R.mipmap.landelini);
//        EMOTION_CLASSIC_MAP.put("[委屈]", R.mipmap.nanguo);
//        EMOTION_CLASSIC_MAP.put("[睡觉]", R.mipmap.shuijiao);
//        EMOTION_CLASSIC_MAP.put("[生气]", R.mipmap.shengqi);
//        EMOTION_CLASSIC_MAP.put("[挤眼]", R.mipmap.nizhidaode);
//        EMOTION_CLASSIC_MAP.put("[失望]", R.mipmap.shiwang);
//        EMOTION_CLASSIC_MAP.put("[捂脸]", R.mipmap.wulian);
//        EMOTION_CLASSIC_MAP.put("[感冒]", R.mipmap.ganmao);
//        EMOTION_CLASSIC_MAP.put("[拜拜]", R.mipmap.baibai);
    }
    /**
     * 根据名称获取当前表情图标R值
     * @param EmotionType 表情类型标志符
     * @param imgName 名称
     * @return
     */
    public static int getImgByName(int EmotionType,String imgName) {
        Integer integer=null;
        switch (EmotionType){
            case EMOTION_CLASSIC_TYPE:
                integer = EMOTION_CLASSIC_MAP.get(imgName);
                break;
            default:
                Log.e("EmotionUtils","the emojiMap is null!!");
                break;
        }
        return integer == null ? -1 : integer;
    }
    /**
     * 根据类型获取表情数据
     * @param EmotionType
     * @return
     */
    public static ArrayMap<String, Integer> getEmojiMap(int EmotionType){
        ArrayMap EmojiMap=null;
        switch (EmotionType){
            case EMOTION_CLASSIC_TYPE:
                EmojiMap=EMOTION_CLASSIC_MAP;
                break;
            default:
                EmojiMap=EMPTY_MAP;
                break;
        }
        return EmojiMap;
    }
}
