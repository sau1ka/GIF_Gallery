package com.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Downsized {

@SerializedName("height")
@Expose
private String height;
@SerializedName("size")
@Expose
private String size;
@SerializedName("url")
@Expose
private String url;
@SerializedName("width")
@Expose
private String width;

public String getHeight() {
return height;
}

public void setHeight(String height) {
this.height = height;
}

public Downsized withHeight(String height) {
this.height = height;
return this;
}

public String getSize() {
return size;
}

public void setSize(String size) {
this.size = size;
}

public Downsized withSize(String size) {
this.size = size;
return this;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public Downsized withUrl(String url) {
this.url = url;
return this;
}

public String getWidth() {
return width;
}

public void setWidth(String width) {
this.width = width;
}

public Downsized withWidth(String width) {
this.width = width;
return this;
}

}