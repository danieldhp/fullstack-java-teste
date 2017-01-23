(function () {
    'use strict';

    var app = angular.module('TesteContabilizei');

    app.config(['$logProvider', function ($logProvider) {
        // turn debugging off/on (no info or warn)
        if ($logProvider.debugEnabled) {
            $logProvider.debugEnabled(true);
        }
    }]);

    /**
     * Intercepta todas as requisições
     */
    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common = {
            Accept: "application/json, text/plain, */*"
        };
        $httpProvider.defaults.headers.post = {
            "Content-Type": "application/json;charset=utf-8"
        };

        // Intercept POST requests, convert to standard
        // form encoding
        $httpProvider.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";
        $httpProvider.defaults.transformRequest.unshift(function (data) {
            var result = [];
            result.push(angular.toJson(data));
            return result.join("&");
        });
    }]);
})();