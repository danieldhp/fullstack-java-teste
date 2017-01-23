(function () {
    'use strict';

    var app = angular.module('TesteContabilizei');

    // Collect the routes
    app.constant('routes', getRoutes());

    app.config(['$stateProvider', '$urlRouterProvider', 'routes', routeConfigurator]);
    function routeConfigurator($stateProvider, $urlRouterProvider, routes) {

        // Rota padrão
        $urlRouterProvider.otherwise(function ($injector) {
            var $state = $injector.get("$state");
            $state.go("cliente");
        });

        routes.forEach(function (r) {
            $stateProvider.state(r.state, r.config);
        });
    }

    // Define the routes
    function getRoutes() {
        return [
            {
                state: 'cliente',
                config: {
                    url: '/cliente',
                    cache: false,
                    templateUrl: 'app/cliente/view/cliente.html',
                    controller: 'clienteController as vm'
                }
            },
            {
                state: 'notaFiscal',
                config: {
                    url: '/notaFiscal',
                    cache: false,
                    templateUrl: 'app/notaFiscal/view/notaFiscal.html',
                    controller: 'notaFiscalController as vm'
                }
            },
            {
                state: 'calculoImpostos',
                config: {
                    url: '/calculoImpostos',
                    cache: false,
                    templateUrl: 'app/calculoImpostos/view/calculoImpostos.html',
                    controller: 'calculoImpostosController as vm'
                }
            },
            {
                state: 'consultaImpostos',
                config: {
                    url: '/consultaImpostos',
                    cache: false,
                    templateUrl: 'app/consultaImpostos/view/consultaImpostos.html',
                    controller: 'consultaImpostosController as vm'
                }
            }
        ];
    }
})();
