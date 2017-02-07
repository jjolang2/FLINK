package com.example.juhyeon.flink.model;

import java.util.ArrayList;

/**
 * 다음 오픈 API중 이미지 검색 결과를 받는 클래스
 */

public class ResponseDaumAPI_SearchImage {
    //가장 바깥 클래스
    channel channel;
    public class channel{
        //그 안쪽 클래스
        String result;
        String pageCount;
        String title;
        String totalCount;
        String lastBuildDate;
        String link;
        String generator;
        String description;

        ArrayList<item> item;
        //안에 item 형식만 가능!
        public class item
        {
            String pubDate;
            String title;
            String thumbnail;
            String cp;
            String height;
            String link;
            String width;
            String image;
            String cpname;

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCpname() {
                return cpname;
            }

            public void setCpname(String cpname) {
                this.cpname = cpname;
            }
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getLastBuildDate() {
            return lastBuildDate;
        }

        public void setLastBuildDate(String lastBuildDate) {
            this.lastBuildDate = lastBuildDate;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getGenerator() {
            return generator;
        }

        public void setGenerator(String generator) {
            this.generator = generator;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<ResponseDaumAPI_SearchImage.channel.item> getItem() {
            return item;
        }

        public void setItem(ArrayList<ResponseDaumAPI_SearchImage.channel.item> item) {
            this.item = item;
        }
    }

    public ResponseDaumAPI_SearchImage.channel getChannel() {
        return channel;
    }

    public void setChannel(ResponseDaumAPI_SearchImage.channel channel) {
        this.channel = channel;
    }
}
