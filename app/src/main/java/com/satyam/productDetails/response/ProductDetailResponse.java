package com.satyam.productDetails.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getIs_address_update() {
            return is_address_update;
        }

        public void setIs_address_update(String is_address_update) {
            this.is_address_update = is_address_update;
        }

        @SerializedName("is_address_update")
        @Expose
        private String is_address_update;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("permalink")
        @Expose
        private String permalink;

        public boolean isIs_cart() {
            return is_cart;
        }

        public void setIs_cart(boolean is_cart) {
            this.is_cart = is_cart;
        }

        @SerializedName("is_cart")
        @Expose
        private boolean is_cart;
        @SerializedName("date_created")
        @Expose
        private String dateCreated;
        @SerializedName("date_created_gmt")
        @Expose
        private String dateCreatedGmt;
        @SerializedName("date_modified")
        @Expose
        private String dateModified;
        @SerializedName("date_modified_gmt")
        @Expose
        private String dateModifiedGmt;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("status")
        @Expose
        private String status;

        public String getCart_total() {
            return cart_total;
        }

        public void setCart_total(String cart_total) {
            this.cart_total = cart_total;
        }

        @SerializedName("cart_total")
        @Expose
        private String cart_total;
        @SerializedName("featured")
        @Expose
        private Boolean featured;
        @SerializedName("catalog_visibility")
        @Expose
        private String catalogVisibility;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("short_description")
        @Expose
        private String shortDescription;
        @SerializedName("sku")
        @Expose
        private String sku;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("regular_price")
        @Expose
        private String regularPrice;
        @SerializedName("sale_price")
        @Expose
        private String salePrice;
        @SerializedName("date_on_sale_from")
        @Expose
        private Object dateOnSaleFrom;
        @SerializedName("date_on_sale_from_gmt")
        @Expose
        private Object dateOnSaleFromGmt;
        @SerializedName("date_on_sale_to")
        @Expose
        private Object dateOnSaleTo;
        @SerializedName("date_on_sale_to_gmt")
        @Expose
        private Object dateOnSaleToGmt;
        @SerializedName("price_html")
        @Expose
        private String priceHtml;
        @SerializedName("on_sale")
        @Expose
        private Boolean onSale;
        @SerializedName("purchasable")
        @Expose
        private Boolean purchasable;
        @SerializedName("total_sales")
        @Expose
        private Integer totalSales;
        @SerializedName("virtual")
        @Expose
        private Boolean virtual;
        @SerializedName("downloadable")
        @Expose
        private Boolean downloadable;
        @SerializedName("downloads")
        @Expose
        private List<Object> downloads = null;
        @SerializedName("download_limit")
        @Expose
        private Integer downloadLimit;
        @SerializedName("download_expiry")
        @Expose
        private Integer downloadExpiry;
        @SerializedName("external_url")
        @Expose
        private String externalUrl;
        @SerializedName("button_text")
        @Expose
        private String buttonText;
        @SerializedName("tax_status")
        @Expose
        private String taxStatus;
        @SerializedName("tax_class")
        @Expose
        private String taxClass;
        @SerializedName("manage_stock")
        @Expose
        private Boolean manageStock;
        @SerializedName("stock_quantity")
        @Expose
        private Integer stockQuantity;
        @SerializedName("stock_status")
        @Expose
        private String stockStatus;
        @SerializedName("backorders")
        @Expose
        private String backorders;
        @SerializedName("backorders_allowed")
        @Expose
        private Boolean backordersAllowed;
        @SerializedName("backordered")
        @Expose
        private Boolean backordered;
        @SerializedName("sold_individually")
        @Expose
        private Boolean soldIndividually;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("dimensions")
        @Expose
        private Dimensions dimensions;
        @SerializedName("shipping_required")
        @Expose
        private Boolean shippingRequired;
        @SerializedName("shipping_taxable")
        @Expose
        private Boolean shippingTaxable;
        @SerializedName("shipping_class")
        @Expose
        private String shippingClass;
        @SerializedName("shipping_class_id")
        @Expose
        private Integer shippingClassId;
        @SerializedName("reviews_allowed")
        @Expose
        private Boolean reviewsAllowed;
        @SerializedName("average_rating")
        @Expose
        private String averageRating;
        @SerializedName("rating_count")
        @Expose
        private Integer ratingCount;
        @SerializedName("related_ids")
        @Expose
        private List<RelatedId> relatedIds = null;
        @SerializedName("upsell_ids")
        @Expose
        private List<Object> upsellIds = null;
        @SerializedName("cross_sell_ids")
        @Expose
        private List<Object> crossSellIds = null;
        @SerializedName("parent_id")
        @Expose
        private Integer parentId;
        @SerializedName("purchase_note")
        @Expose
        private String purchaseNote;
        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("tags")
        @Expose
        private List<Object> tags = null;
        @SerializedName("images")
        @Expose
        private List<Image> images = null;
        @SerializedName("attributes")
        @Expose
        private List<Attribute> attributes = null;
        @SerializedName("default_attributes")
        @Expose
        private List<Object> defaultAttributes = null;
        @SerializedName("variations")
        @Expose
        private List<Integer> variations = null;
        @SerializedName("grouped_products")
        @Expose
        private List<Object> groupedProducts = null;

        @SerializedName("custom_variations")
        @Expose
        private List<CustomVariation> customVariations = null;

        public List<CustomVariation> getCustomVariations() {
            return customVariations;
        }

        public void setCustomVariations(List<CustomVariation> customVariations) {
            this.customVariations = customVariations;
        }

        @SerializedName("menu_order")
        @Expose
        private Integer menuOrder;
        @SerializedName("meta_data")
        @Expose
        private List<MetaDatum> metaData = null;
        @SerializedName("store")
        @Expose
        private Store store;
        @SerializedName("_links")
        @Expose
        private Links links;
        @SerializedName("colors")
        @Expose
        private List<String> colors = null;

        public List<String> getColors() {
            return colors;
        }

        public void setColors(List<String> colors) {
            this.colors = colors;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getPermalink() {
            return permalink;
        }

        public void setPermalink(String permalink) {
            this.permalink = permalink;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getDateCreatedGmt() {
            return dateCreatedGmt;
        }

        public void setDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getDateModifiedGmt() {
            return dateModifiedGmt;
        }

        public void setDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Boolean getFeatured() {
            return featured;
        }

        public void setFeatured(Boolean featured) {
            this.featured = featured;
        }

        public String getCatalogVisibility() {
            return catalogVisibility;
        }

        public void setCatalogVisibility(String catalogVisibility) {
            this.catalogVisibility = catalogVisibility;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRegularPrice() {
            return regularPrice;
        }

        public void setRegularPrice(String regularPrice) {
            this.regularPrice = regularPrice;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public Object getDateOnSaleFrom() {
            return dateOnSaleFrom;
        }

        public void setDateOnSaleFrom(Object dateOnSaleFrom) {
            this.dateOnSaleFrom = dateOnSaleFrom;
        }

        public Object getDateOnSaleFromGmt() {
            return dateOnSaleFromGmt;
        }

        public void setDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
            this.dateOnSaleFromGmt = dateOnSaleFromGmt;
        }

        public Object getDateOnSaleTo() {
            return dateOnSaleTo;
        }

        public void setDateOnSaleTo(Object dateOnSaleTo) {
            this.dateOnSaleTo = dateOnSaleTo;
        }

        public Object getDateOnSaleToGmt() {
            return dateOnSaleToGmt;
        }

        public void setDateOnSaleToGmt(Object dateOnSaleToGmt) {
            this.dateOnSaleToGmt = dateOnSaleToGmt;
        }

        public String getPriceHtml() {
            return priceHtml;
        }

        public void setPriceHtml(String priceHtml) {
            this.priceHtml = priceHtml;
        }

        public Boolean getOnSale() {
            return onSale;
        }

        public void setOnSale(Boolean onSale) {
            this.onSale = onSale;
        }

        public Boolean getPurchasable() {
            return purchasable;
        }

        public void setPurchasable(Boolean purchasable) {
            this.purchasable = purchasable;
        }

        public Integer getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(Integer totalSales) {
            this.totalSales = totalSales;
        }

        public Boolean getVirtual() {
            return virtual;
        }

        public void setVirtual(Boolean virtual) {
            this.virtual = virtual;
        }

        public Boolean getDownloadable() {
            return downloadable;
        }

        public void setDownloadable(Boolean downloadable) {
            this.downloadable = downloadable;
        }

        public List<Object> getDownloads() {
            return downloads;
        }

        public void setDownloads(List<Object> downloads) {
            this.downloads = downloads;
        }

        public Integer getDownloadLimit() {
            return downloadLimit;
        }

        public void setDownloadLimit(Integer downloadLimit) {
            this.downloadLimit = downloadLimit;
        }

        public Integer getDownloadExpiry() {
            return downloadExpiry;
        }

        public void setDownloadExpiry(Integer downloadExpiry) {
            this.downloadExpiry = downloadExpiry;
        }

        public String getExternalUrl() {
            return externalUrl;
        }

        public void setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
        }

        public String getButtonText() {
            return buttonText;
        }

        public void setButtonText(String buttonText) {
            this.buttonText = buttonText;
        }

        public String getTaxStatus() {
            return taxStatus;
        }

        public void setTaxStatus(String taxStatus) {
            this.taxStatus = taxStatus;
        }

        public String getTaxClass() {
            return taxClass;
        }

        public void setTaxClass(String taxClass) {
            this.taxClass = taxClass;
        }

        public Boolean getManageStock() {
            return manageStock;
        }

        public void setManageStock(Boolean manageStock) {
            this.manageStock = manageStock;
        }

        public Integer getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        public String getStockStatus() {
            return stockStatus;
        }

        public void setStockStatus(String stockStatus) {
            this.stockStatus = stockStatus;
        }

        public String getBackorders() {
            return backorders;
        }

        public void setBackorders(String backorders) {
            this.backorders = backorders;
        }

        public Boolean getBackordersAllowed() {
            return backordersAllowed;
        }

        public void setBackordersAllowed(Boolean backordersAllowed) {
            this.backordersAllowed = backordersAllowed;
        }

        public Boolean getBackordered() {
            return backordered;
        }

        public void setBackordered(Boolean backordered) {
            this.backordered = backordered;
        }

        public Boolean getSoldIndividually() {
            return soldIndividually;
        }

        public void setSoldIndividually(Boolean soldIndividually) {
            this.soldIndividually = soldIndividually;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public Dimensions getDimensions() {
            return dimensions;
        }

        public void setDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
        }

        public Boolean getShippingRequired() {
            return shippingRequired;
        }

        public void setShippingRequired(Boolean shippingRequired) {
            this.shippingRequired = shippingRequired;
        }

        public Boolean getShippingTaxable() {
            return shippingTaxable;
        }

        public void setShippingTaxable(Boolean shippingTaxable) {
            this.shippingTaxable = shippingTaxable;
        }

        public String getShippingClass() {
            return shippingClass;
        }

        public void setShippingClass(String shippingClass) {
            this.shippingClass = shippingClass;
        }

        public Integer getShippingClassId() {
            return shippingClassId;
        }

        public void setShippingClassId(Integer shippingClassId) {
            this.shippingClassId = shippingClassId;
        }

        public Boolean getReviewsAllowed() {
            return reviewsAllowed;
        }

        public void setReviewsAllowed(Boolean reviewsAllowed) {
            this.reviewsAllowed = reviewsAllowed;
        }

        public String getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(String averageRating) {
            this.averageRating = averageRating;
        }

        public Integer getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
        }

        public List<RelatedId> getRelatedIds() {
            return relatedIds;
        }

        public void setRelatedIds(List<RelatedId> relatedIds) {
            this.relatedIds = relatedIds;
        }

        public List<Object> getUpsellIds() {
            return upsellIds;
        }

        public void setUpsellIds(List<Object> upsellIds) {
            this.upsellIds = upsellIds;
        }

        public List<Object> getCrossSellIds() {
            return crossSellIds;
        }

        public void setCrossSellIds(List<Object> crossSellIds) {
            this.crossSellIds = crossSellIds;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getPurchaseNote() {
            return purchaseNote;
        }

        public void setPurchaseNote(String purchaseNote) {
            this.purchaseNote = purchaseNote;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public List<Object> getTags() {
            return tags;
        }

        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public List<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }

        public List<Object> getDefaultAttributes() {
            return defaultAttributes;
        }

        public void setDefaultAttributes(List<Object> defaultAttributes) {
            this.defaultAttributes = defaultAttributes;
        }


        public List<Object> getGroupedProducts() {
            return groupedProducts;
        }

        public void setGroupedProducts(List<Object> groupedProducts) {
            this.groupedProducts = groupedProducts;
        }

        public Integer getMenuOrder() {
            return menuOrder;
        }

        public void setMenuOrder(Integer menuOrder) {
            this.menuOrder = menuOrder;
        }

        public List<MetaDatum> getMetaData() {
            return metaData;
        }

        public void setMetaData(List<MetaDatum> metaData) {
            this.metaData = metaData;
        }

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public class MetaDatum {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("key")
            @Expose
            private String key;


            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }

        public class RelatedId {

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

        public class Image {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("date_created")
            @Expose
            private String dateCreated;
            @SerializedName("date_created_gmt")
            @Expose
            private String dateCreatedGmt;
            @SerializedName("date_modified")
            @Expose
            private String dateModified;
            @SerializedName("date_modified_gmt")
            @Expose
            private String dateModifiedGmt;
            @SerializedName("src")
            @Expose
            private String src;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("alt")
            @Expose
            private String alt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getDateCreated() {
                return dateCreated;
            }

            public void setDateCreated(String dateCreated) {
                this.dateCreated = dateCreated;
            }

            public String getDateCreatedGmt() {
                return dateCreatedGmt;
            }

            public void setDateCreatedGmt(String dateCreatedGmt) {
                this.dateCreatedGmt = dateCreatedGmt;
            }

            public String getDateModified() {
                return dateModified;
            }

            public void setDateModified(String dateModified) {
                this.dateModified = dateModified;
            }

            public String getDateModifiedGmt() {
                return dateModifiedGmt;
            }

            public void setDateModifiedGmt(String dateModifiedGmt) {
                this.dateModifiedGmt = dateModifiedGmt;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

        }


        public class Category {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("slug")
            @Expose
            private String slug;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

        }

        public class Dimensions {

            @SerializedName("length")
            @Expose
            private String length;
            @SerializedName("width")
            @Expose
            private String width;
            @SerializedName("height")
            @Expose
            private String height;

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

        }

        public class Store {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("shop_name")
            @Expose
            private String shopName;
            @SerializedName("url")
            @Expose
            private String url;
           /* @SerializedName("address")
            @Expose
            private Address address;*/

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

           /* public Address getAddress() {
                return address;
            }

            public void setAddress(Address address) {
                this.address = address;
            }*/

            public class Address {

                @SerializedName("street_1")
                @Expose
                private String street1;
                @SerializedName("street_2")
                @Expose
                private String street2;
                @SerializedName("city")
                @Expose
                private String city;
                @SerializedName("zip")
                @Expose
                private String zip;
                @SerializedName("state")
                @Expose
                private String state;
                @SerializedName("country")
                @Expose
                private String country;

                public String getStreet1() {
                    return street1;
                }

                public void setStreet1(String street1) {
                    this.street1 = street1;
                }

                public String getStreet2() {
                    return street2;
                }

                public void setStreet2(String street2) {
                    this.street2 = street2;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getZip() {
                    return zip;
                }

                public void setZip(String zip) {
                    this.zip = zip;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

            }
        }

        public class Links {

            @SerializedName("self")
            @Expose
            private List<Self> self = null;
            @SerializedName("collection")
            @Expose
            private List<Collection> collection = null;

            public List<Self> getSelf() {
                return self;
            }

            public void setSelf(List<Self> self) {
                this.self = self;
            }

            public List<Collection> getCollection() {
                return collection;
            }

            public void setCollection(List<Collection> collection) {
                this.collection = collection;
            }

            public class Self {

                @SerializedName("href")
                @Expose
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }

            }

            public class Collection {

                @SerializedName("href")
                @Expose
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }

            }
        }

        public class Attribute {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("position")
            @Expose
            private Integer position;
            @SerializedName("visible")
            @Expose
            private Boolean visible;
            @SerializedName("variation")
            @Expose
            private Boolean variation;
            @SerializedName("options")
            @Expose
            private List<String> options = null;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getPosition() {
                return position;
            }

            public void setPosition(Integer position) {
                this.position = position;
            }

            public Boolean getVisible() {
                return visible;
            }

            public void setVisible(Boolean visible) {
                this.visible = visible;
            }

            public Boolean getVariation() {
                return variation;
            }

            public void setVariation(Boolean variation) {
                this.variation = variation;
            }

            public List<String> getOptions() {
                return options;
            }

            public void setOptions(List<String> options) {
                this.options = options;
            }
        }

        public class CustomVariation {

            @SerializedName("key")
            @Expose
            private String key;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("options")
            @Expose
            private List<Option> options = null;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<Option> getOptions() {
                return options;
            }

            public void setOptions(List<Option> options) {
                this.options = options;
            }

            public class Option {

                @SerializedName("key")
                @Expose
                private String key;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                @SerializedName("k")
                @Expose
                private String k;
                @SerializedName("v")
                @Expose
                private String v;
                @SerializedName("vp")
                @Expose
                private String vp;

                public String getK() {
                    return k;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getVp() {
                    return vp;
                }

                public void setVp(String vp) {
                    this.vp = vp;
                }

            }

        }
    }
}