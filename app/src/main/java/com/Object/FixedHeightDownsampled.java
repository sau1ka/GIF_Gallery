package com.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FixedHeightDownsampled {

@SerializedName("height")
@Expose
private String height;
@SerializedName("size")
@Expose
private String size;
@SerializedName("url")
@Expose
private String url;
@SerializedName("webp")
@Expose
private String webp;
@SerializedName("webp_size")
@Expose
private String webpSize;
@SerializedName("width")
@Expose
private String width;

public String getHeight() {
return height;
}

public void setHeight(String height) {
this.height = height;
}

public FixedHeightDownsampled withHeight(String height) {
this.height = height;
return this;
}

public String getSize() {
return size;
}

public void setSize(String size) {
this.size = size;
}

public FixedHeightDownsampled withSize(String size) {
this.size = size;
return this;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public FixedHeightDownsampled withUrl(String url) {
this.url = url;
return this;
}

public String getWebp() {
return webp;
}

public void setWebp(String webp) {
this.webp = webp;
}

public FixedHeightDownsampled withWebp(String webp) {
this.webp = webp;
return this;
}

public String getWebpSize() {
return webpSize;
}

public void setWebpSize(String webpSize) {
this.webpSize = webpSize;
}

public FixedHeightDownsampled withWebpSize(String webpSize) {
this.webpSize = webpSize;
return this;
}

public String getWidth() {
return width;
}

public void setWidth(String width) {
this.width = width;
}

public FixedHeightDownsampled withWidth(String width) {
this.width = width;
return this;
}

}