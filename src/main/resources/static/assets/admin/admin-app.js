app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl:"/TechnoShop/assets/admin/product/index.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl:"/TechnoShop/assets/admin/authority/index.html",
//           controller:"authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl:"/TechnoShop/assets/admin/authority/unauthorized.html",
//            controller: "authority-ctrl"
        })
       .when("/account", {
            templateUrl:"/TechnoShop/assets/admin/account/index.html",
            controller: "account-ctrl"
        })
        .when("/category", {
            templateUrl:"/TechnoShop/assets/admin/category/index.html",
            controller: "category-ctrl"
        })
         .when("/order", {
            templateUrl:"/TechnoShop/assets/admin/order/index.html",
            controller: "order-ctrl"
        })
        .when("/report", {
            templateUrl:"/TechnoShop/assets/admin/report/index.html",
//            controller: "report-ctrl"
        })
         .when("/new", {
            templateUrl:"/TechnoShop/assets/admin/new/index.html",
            controller: "new-ctrl"
        })
         .when("/review", {
            templateUrl:"/TechnoShop/assets/admin/review/index.html",
            controller: "review-ctrl"
        })
         .when("/screen", {
            templateUrl:"/TechnoShop/assets/admin/screen/index.html",
            controller: "screen-ctrl"
        })
        .when("/connect", {
            templateUrl:"/TechnoShop/assets/admin/connect/index.html",
            controller: "connect-ctrl"
        })
         .when("/utility", {
            templateUrl:"/TechnoShop/assets/admin/utility/index.html",
            controller: "utility-ctrl"
        })
         .when("/general", {
            templateUrl:"/TechnoShop/assets/admin/general/index.html",
           controller: "general-ctrl"
        })
        .when("/battery", {
            templateUrl:"/TechnoShop/assets/admin/battery/index.html",
           controller: "battery-ctrl"
        })
        .otherwise({
            templateUrl:"/TechnoShop/assets/admin/home/home.html",
            controller: "home-ctrl"
        });
});