package com.chenzhanyang.mvpsample.model.bean;

import com.tcl.smarthome.mvplibrary.model.bean.Bean;

import java.util.List;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/5.
 */

public class PicListBean extends Bean{

    /**
     * status : true
     * total : 313
     * tngou : [{"count":70101,"fcount":0,"galleryclass":1,"id":1035,"img":"/ext/161223/395b860c06ccaf5b35cde317ff082c18.jpg","rcount":0,"size":9,"time":1482494660000,"title":"蕾丝透视装美女性感包臀裙极品私房照"},{"count":35125,"fcount":0,"galleryclass":1,"id":1032,"img":"/ext/161213/a94ead894d0d0e4e5b3b807626eeab4d.jpg","rcount":0,"size":10,"time":1481628573000,"title":"大胸美女御姐酥胸事业线美腿妖娆性感"},{"count":18953,"fcount":0,"galleryclass":1,"id":1030,"img":"/ext/161211/93a8c952a96f75389f2e9a0d6846ca6e.jpg","rcount":0,"size":9,"time":1481426601000,"title":"蕾丝美女大胆极品透视装性感私房写真图片"},{"count":10965,"fcount":0,"galleryclass":1,"id":1025,"img":"/ext/161209/6cc26c6f440c091e0cf78229a9642929.jpg","rcount":0,"size":10,"time":1481282052000,"title":"性感洋娃娃yurisa闺房爆乳翘臀靓丽"},{"count":14708,"fcount":0,"galleryclass":1,"id":1021,"img":"/ext/161202/be4ee41db8e48be69ed47ce34db612ab.jpg","rcount":0,"size":7,"time":1480682472000,"title":"大胸美女酸酱兔裹身裙大尺度爆乳酥胸性感"},{"count":7874,"fcount":0,"galleryclass":1,"id":1020,"img":"/ext/161127/e0f476a787c02338aa21d685808780f4.jpg","rcount":0,"size":9,"time":1480244937000,"title":"性感美少女情趣内衣私房性感迷人写真图片"},{"count":6013,"fcount":0,"galleryclass":1,"id":1019,"img":"/ext/161127/38b16f1831bb3ea6a44759371d070985.jpg","rcount":0,"size":6,"time":1480244882000,"title":"清新玉女陈大榕清纯白衬衫私房大胆人体性感"},{"count":4984,"fcount":0,"galleryclass":1,"id":1018,"img":"/ext/161127/c3f698ff5d050d2402d5333df61d2c43.jpg","rcount":0,"size":16,"time":1480244841000,"title":"大胸美女容容容Alice蕾丝美腿私拍"},{"count":6156,"fcount":0,"galleryclass":1,"id":1017,"img":"/ext/161120/8499e465dce9b1e50890574bb396c4c9.jpg","rcount":0,"size":20,"time":1479642881000,"title":"爆乳酥胸美女方绮言Ayaka性感旅拍"},{"count":5699,"fcount":0,"galleryclass":1,"id":1009,"img":"/ext/161116/220ace703d56e12f0d7b9b2ab3e3e807.jpg","rcount":0,"size":7,"time":1479295847000,"title":"性感大胸尤物许诺极致诱惑私房撩人性感写真"}]
     */

    private boolean status;
    private int total;
    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        /**
         * count : 70101
         * fcount : 0
         * galleryclass : 1
         * id : 1035
         * img : /ext/161223/395b860c06ccaf5b35cde317ff082c18.jpg
         * rcount : 0
         * size : 9
         * time : 1482494660000
         * title : 蕾丝透视装美女性感包臀裙极品私房照
         */

        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
