(function () {
    'use strict';

    angular.module('TesteContabilizei.util').service('utilService', UtilService);

    UtilService.$inject = ['$http', '$q'];

    function UtilService($http, $q) {

        var service = {
            post: post,
            validarEmail: validarEmail,
            validarCnpj: validarCnpj
        };

        return service;

        /**
         * Metodo para realizar uma chamada post com dados a uma url
         *
         * @param url
         * @param dados
         * @returns
         */
        function post(url, dados) {
            var deferred = $q.defer();
            if (!dados) {
                dados = null;
            }

            var response = $http({
                method: 'POST',
                url: url,
                data: dados,
                timeout: deferred.promise,
                cancel: deferred
            }).then(function (data) {
                deferred.resolve(data.data);
            }, function (data) {
                deferred.reject(data);
            });

            return deferred.promise;
        }

        function validarEmail(email) {
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        function validarCnpj(cnpj) {
            cnpj = cnpj.replace(/[^\d]+/g, '');

            if (cnpj === '') {
                return false;
            }

            if (cnpj.length !== 14) {
                return false;
            }
            /* jshint ignore:start */
            if (cnpj == "00000000000000" ||
                cnpj == "11111111111111" ||
                cnpj == "22222222222222" ||
                cnpj == "33333333333333" ||
                cnpj == "44444444444444" ||
                cnpj == "55555555555555" ||
                cnpj == "66666666666666" ||
                cnpj == "77777777777777" ||
                cnpj == "88888888888888" ||
                cnpj == "99999999999999")
                return false;


            var tamanho = cnpj.length - 2
            var numeros = cnpj.substring(0, tamanho);
            var digitos = cnpj.substring(tamanho);
            var soma = 0;
            var pos = tamanho - 7;
            for (var i = tamanho; i >= 1; i--) {
                soma += numeros.charAt(tamanho - i) * pos--;
                if (pos < 2)
                    pos = 9;
            }
            var resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(0))
                return false;

            tamanho = tamanho + 1;
            numeros = cnpj.substring(0, tamanho);
            soma = 0;
            pos = tamanho - 7;
            for (i = tamanho; i >= 1; i--) {
                soma += numeros.charAt(tamanho - i) * pos--;
                if (pos < 2)
                    pos = 9;
            }
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(1))
                return false;

            return true;
            /* jshint ignore:end */
        }
    }
})();

