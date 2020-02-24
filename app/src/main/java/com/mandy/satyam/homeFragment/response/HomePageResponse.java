package com.mandy.satyam.homeFragment.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomePageResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getCart_total() {
        return cart_total;
    }

    public void setCart_total(String cart_total) {
        this.cart_total = cart_total;
    }

    @SerializedName("cart_total")
    @Expose
    private String cart_total;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("categories_count")
        @Expose
        private Integer categoriesCount;
        @SerializedName("banners")
        @Expose
        private List<Banner> banners = null;
        @SerializedName("sections")
        @Expose
        private List<Section> sections = null;

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public Integer getCategoriesCount() {
            return categoriesCount;
        }

        public void setCategoriesCount(Integer categoriesCount) {
            this.categoriesCount = categoriesCount;
        }

        public List<Banner> getBanners() {
            return banners;
        }

        public void setBanners(List<Banner> banners) {
            this.banners = banners;
        }

        public List<Section> getSections() {
            return sections;
        }

        public void setSections(List<Section> sections) {
            this.sections = sections;
        }

        public class Category {

            @SerializedName("category_id")
            @Expose
            private Integer categoryId;
            @SerializedName("category_name")
            @Expose
            private String categoryName;

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

        }

        public class Banner {

            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("link")
            @Expose
            private String link;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

        }

        public class Section {

            @SerializedName("category_id")
            @Expose
            private Integer categoryId;
            @SerializedName("category_title")
            @Expose
            private String categoryTitle;
            @SerializedName("category_products")
            @Expose
            private List<CategoryProduct> categoryProducts = null;

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryTitle() {
                return categoryTitle;
            }

            public void setCategoryTitle(String categoryTitle) {
                this.categoryTitle = categoryTitle;
            }

            public List<CategoryProduct> getCategoryProducts() {
                return categoryProducts;
            }

            public void setCategoryProducts(List<CategoryProduct> categoryProducts) {
                this.categoryProducts = categoryProducts;
            }

            public class CategoryProduct {

                @SerializedName("product_id")
                @Expose
                private Integer productId;
                @SerializedName("product_name")
                @Expose
                private String productName;
                @SerializedName("product_price")
                @Expose
                private String productPrice;
                @SerializedName("product_regular_price")
                @Expose
                private String productRegularPrice;
                @SerializedName("product_sale_price")
                @Expose
                private String productSalePrice;
                @SerializedName("product_date_on_sale_from")
                @Expose
                private Object productDateOnSaleFrom;
                @SerializedName("product_date_on_sale_to")
                @Expose
                private Object productDateOnSaleTo;
                @SerializedName("product_image")
                @Expose
                private String productImage;
                @SerializedName("currency")
                @Expose
                private String currency;
                @SerializedName("currency_symbol")
                @Expose
                private String currencySymbol;

                public Integer getProductId() {
                    return productId;
                }

                public void setProductId(Integer productId) {
                    this.productId = productId;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getProductPrice() {
                    return productPrice;
                }

                public void setProductPrice(String productPrice) {
                    this.productPrice = productPrice;
                }

                public String getProductRegularPrice() {
                    return productRegularPrice;
                }

                public void setProductRegularPrice(String productRegularPrice) {
                    this.productRegularPrice = productRegularPrice;
                }

                public String getProductSalePrice() {
                    return productSalePrice;
                }

                public void setProductSalePrice(String productSalePrice) {
                    this.productSalePrice = productSalePrice;
                }

                public Object getProductDateOnSaleFrom() {
                    return productDateOnSaleFrom;
                }

                public void setProductDateOnSaleFrom(Object productDateOnSaleFrom) {
                    this.productDateOnSaleFrom = productDateOnSaleFrom;
                }

                public Object getProductDateOnSaleTo() {
                    return productDateOnSaleTo;
                }

                public void setProductDateOnSaleTo(Object productDateOnSaleTo) {
                    this.productDateOnSaleTo = productDateOnSaleTo;
                }

                public String getProductImage() {
                    return productImage;
                }

                public void setProductImage(String productImage) {
                    this.productImage = productImage;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public String getCurrencySymbol() {
                    return currencySymbol;
                }

                public void setCurrencySymbol(String currencySymbol) {
                    this.currencySymbol = currencySymbol;
                }

            }
        }
    }
}