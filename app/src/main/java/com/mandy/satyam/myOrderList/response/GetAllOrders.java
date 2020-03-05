package com.mandy.satyam.myOrderList.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllOrders {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("parent_id")
        @Expose
        private Integer parentId;
        @SerializedName("number")
        @Expose
        private String number;
        @SerializedName("order_key")
        @Expose
        private String orderKey;
        @SerializedName("created_via")
        @Expose
        private String createdVia;
        @SerializedName("version")
        @Expose
        private String version;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("date_created")
        @Expose
        private String dateCreated;
        @SerializedName("date_created_gmt")
        @Expose
        private String dateCreatedGmt;
        @SerializedName("date_modified")
        @Expose
        private String dateModified;

        public String getDate_created_miliseconds() {
            return date_created_miliseconds;
        }

        public void setDate_created_miliseconds(String date_created_miliseconds) {
            this.date_created_miliseconds = date_created_miliseconds;
        }

        @SerializedName("date_created_miliseconds")
        @Expose
        private String date_created_miliseconds;
        @SerializedName("date_modified_gmt")
        @Expose
        private String dateModifiedGmt;
        @SerializedName("discount_total")
        @Expose
        private String discountTotal;
        @SerializedName("discount_tax")
        @Expose
        private String discountTax;
        @SerializedName("shipping_total")
        @Expose
        private String shippingTotal;
        @SerializedName("shipping_tax")
        @Expose
        private String shippingTax;
        @SerializedName("cart_tax")
        @Expose
        private String cartTax;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("total_tax")
        @Expose
        private String totalTax;
        @SerializedName("prices_include_tax")
        @Expose
        private Boolean pricesIncludeTax;
        @SerializedName("customer_id")
        @Expose
        private Integer customerId;
        @SerializedName("customer_ip_address")
        @Expose
        private String customerIpAddress;
        @SerializedName("customer_user_agent")
        @Expose
        private String customerUserAgent;
        @SerializedName("customer_note")
        @Expose
        private String customerNote;
        @SerializedName("billing")
        @Expose
        private Billing billing;
        @SerializedName("shipping")
        @Expose
        private Shipping shipping;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;
        @SerializedName("payment_method_title")
        @Expose
        private String paymentMethodTitle;
        @SerializedName("transaction_id")
        @Expose
        private String transactionId;
        @SerializedName("date_paid")
        @Expose
        private String datePaid;
        @SerializedName("date_paid_gmt")
        @Expose
        private String datePaidGmt;
        @SerializedName("date_completed")
        @Expose
        private Object dateCompleted;
        @SerializedName("date_completed_gmt")
        @Expose
        private Object dateCompletedGmt;
        @SerializedName("cart_hash")
        @Expose
        private String cartHash;
      /* *//* @SerializedName("meta_data")
        @Expose
        private List<MetaDatum> metaData = null;*/
        @SerializedName("line_items")
        @Expose
        private List<LineItem> lineItems = null;
        @SerializedName("tax_lines")
        @Expose
        private List<Object> taxLines = null;
        @SerializedName("shipping_lines")
        @Expose
        private List<Object> shippingLines = null;
        @SerializedName("fee_lines")
        @Expose
        private List<Object> feeLines = null;
        @SerializedName("coupon_lines")
        @Expose
        private List<Object> couponLines = null;
        @SerializedName("refunds")
        @Expose
        private List<Object> refunds = null;
        @SerializedName("store")
        @Expose
        private Store store;
        @SerializedName("currency_symbol")
        @Expose
        private String currencySymbol;
        @SerializedName("_links")
        @Expose
        private Links links;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrderKey() {
            return orderKey;
        }

        public void setOrderKey(String orderKey) {
            this.orderKey = orderKey;
        }

        public String getCreatedVia() {
            return createdVia;
        }

        public void setCreatedVia(String createdVia) {
            this.createdVia = createdVia;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
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

        public String getDiscountTotal() {
            return discountTotal;
        }

        public void setDiscountTotal(String discountTotal) {
            this.discountTotal = discountTotal;
        }

        public String getDiscountTax() {
            return discountTax;
        }

        public void setDiscountTax(String discountTax) {
            this.discountTax = discountTax;
        }

        public String getShippingTotal() {
            return shippingTotal;
        }

        public void setShippingTotal(String shippingTotal) {
            this.shippingTotal = shippingTotal;
        }

        public String getShippingTax() {
            return shippingTax;
        }

        public void setShippingTax(String shippingTax) {
            this.shippingTax = shippingTax;
        }

        public String getCartTax() {
            return cartTax;
        }

        public void setCartTax(String cartTax) {
            this.cartTax = cartTax;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotalTax() {
            return totalTax;
        }

        public void setTotalTax(String totalTax) {
            this.totalTax = totalTax;
        }

        public Boolean getPricesIncludeTax() {
            return pricesIncludeTax;
        }

        public void setPricesIncludeTax(Boolean pricesIncludeTax) {
            this.pricesIncludeTax = pricesIncludeTax;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public String getCustomerIpAddress() {
            return customerIpAddress;
        }

        public void setCustomerIpAddress(String customerIpAddress) {
            this.customerIpAddress = customerIpAddress;
        }

        public String getCustomerUserAgent() {
            return customerUserAgent;
        }

        public void setCustomerUserAgent(String customerUserAgent) {
            this.customerUserAgent = customerUserAgent;
        }

        public String getCustomerNote() {
            return customerNote;
        }

        public void setCustomerNote(String customerNote) {
            this.customerNote = customerNote;
        }

        public Billing getBilling() {
            return billing;
        }

        public void setBilling(Billing billing) {
            this.billing = billing;
        }

        public Shipping getShipping() {
            return shipping;
        }

        public void setShipping(Shipping shipping) {
            this.shipping = shipping;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getPaymentMethodTitle() {
            return paymentMethodTitle;
        }

        public void setPaymentMethodTitle(String paymentMethodTitle) {
            this.paymentMethodTitle = paymentMethodTitle;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getDatePaid() {
            return datePaid;
        }

        public void setDatePaid(String datePaid) {
            this.datePaid = datePaid;
        }

        public String getDatePaidGmt() {
            return datePaidGmt;
        }

        public void setDatePaidGmt(String datePaidGmt) {
            this.datePaidGmt = datePaidGmt;
        }

        public Object getDateCompleted() {
            return dateCompleted;
        }

        public void setDateCompleted(Object dateCompleted) {
            this.dateCompleted = dateCompleted;
        }

        public Object getDateCompletedGmt() {
            return dateCompletedGmt;
        }

        public void setDateCompletedGmt(Object dateCompletedGmt) {
            this.dateCompletedGmt = dateCompletedGmt;
        }

        public String getCartHash() {
            return cartHash;
        }

        public void setCartHash(String cartHash) {
            this.cartHash = cartHash;
        }

       /* public List<MetaDatum> getMetaData() {
            return metaData;
        }

        public void setMetaData(List<MetaDatum> metaData) {
            this.metaData = metaData;
        }*/

        public List<LineItem> getLineItems() {
            return lineItems;
        }

        public void setLineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
        }

        public List<Object> getTaxLines() {
            return taxLines;
        }

        public void setTaxLines(List<Object> taxLines) {
            this.taxLines = taxLines;
        }

        public List<Object> getShippingLines() {
            return shippingLines;
        }

        public void setShippingLines(List<Object> shippingLines) {
            this.shippingLines = shippingLines;
        }

        public List<Object> getFeeLines() {
            return feeLines;
        }

        public void setFeeLines(List<Object> feeLines) {
            this.feeLines = feeLines;
        }

        public List<Object> getCouponLines() {
            return couponLines;
        }

        public void setCouponLines(List<Object> couponLines) {
            this.couponLines = couponLines;
        }

        public List<Object> getRefunds() {
            return refunds;
        }

        public void setRefunds(List<Object> refunds) {
            this.refunds = refunds;
        }

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public void setCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public class Billing {

            @SerializedName("first_name")
            @Expose
            private String firstName;
            @SerializedName("last_name")
            @Expose
            private String lastName;
            @SerializedName("company")
            @Expose
            private String company;
            @SerializedName("address_1")
            @Expose
            private String address1;
            @SerializedName("address_2")
            @Expose
            private String address2;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("state")
            @Expose
            private String state;
            @SerializedName("postcode")
            @Expose
            private String postcode;
            @SerializedName("country")
            @Expose
            private String country;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("phone")
            @Expose
            private String phone;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

        }

        public class Shipping {

            @SerializedName("first_name")
            @Expose
            private String firstName;
            @SerializedName("last_name")
            @Expose
            private String lastName;
            @SerializedName("company")
            @Expose
            private String company;
            @SerializedName("address_1")
            @Expose
            private String address1;
            @SerializedName("address_2")
            @Expose
            private String address2;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("state")
            @Expose
            private String state;
            @SerializedName("postcode")
            @Expose
            private String postcode;
            @SerializedName("country")
            @Expose
            private String country;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

        }

        /*public class MetaDatum {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("key")
            @Expose
            private String key;
            @SerializedName("value")
            @Expose
            private String value;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

        }*/

        public class LineItem {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("product_id")
            @Expose
            private Integer productId;
            @SerializedName("variation_id")
            @Expose
            private Integer variationId;
            @SerializedName("quantity")
            @Expose
            private Integer quantity;
            @SerializedName("tax_class")
            @Expose
            private String taxClass;
            @SerializedName("subtotal")
            @Expose
            private String subtotal;
            @SerializedName("subtotal_tax")
            @Expose
            private String subtotalTax;
            @SerializedName("total")
            @Expose
            private String total;
            @SerializedName("total_tax")
            @Expose
            private String totalTax;
            @SerializedName("taxes")
            @Expose
            private List<Object> taxes = null;
           /* @SerializedName("meta_data")
            @Expose
            private List<MetaDatum_> metaData = null;*/
            @SerializedName("sku")
            @Expose
            private String sku;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("product_image")
            @Expose
            private String productImage;

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

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public Integer getVariationId() {
                return variationId;
            }

            public void setVariationId(Integer variationId) {
                this.variationId = variationId;
            }

            public Integer getQuantity() {
                return quantity;
            }

            public void setQuantity(Integer quantity) {
                this.quantity = quantity;
            }

            public String getTaxClass() {
                return taxClass;
            }

            public void setTaxClass(String taxClass) {
                this.taxClass = taxClass;
            }

            public String getSubtotal() {
                return subtotal;
            }

            public void setSubtotal(String subtotal) {
                this.subtotal = subtotal;
            }

            public String getSubtotalTax() {
                return subtotalTax;
            }

            public void setSubtotalTax(String subtotalTax) {
                this.subtotalTax = subtotalTax;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTotalTax() {
                return totalTax;
            }

            public void setTotalTax(String totalTax) {
                this.totalTax = totalTax;
            }

            public List<Object> getTaxes() {
                return taxes;
            }

            public void setTaxes(List<Object> taxes) {
                this.taxes = taxes;
            }

            /*public List<MetaDatum_> getMetaData() {
                return metaData;
            }

            public void setMetaData(List<MetaDatum_> metaData) {
                this.metaData = metaData;
            }
*/
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

            public String getProductImage() {
                return productImage;
            }

            public void setProductImage(String productImage) {
                this.productImage = productImage;
            }

            /*public class MetaDatum_ {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("key")
                @Expose
                private String key;
                @SerializedName("value")
                @Expose
                private String value;

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

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

            }*/

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
            @SerializedName("address")
            @Expose
            private Address address;

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

            public Address getAddress() {
                return address;
            }

            public void setAddress(Address address) {
                this.address = address;
            }

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
            @SerializedName("customer")
            @Expose
            private List<Customer> customer = null;

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

            public List<Customer> getCustomer() {
                return customer;
            }

            public void setCustomer(List<Customer> customer) {
                this.customer = customer;
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

            public class Customer {

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
    }
}