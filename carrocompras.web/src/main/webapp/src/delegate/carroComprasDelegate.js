define(['delegate/_carroComprasDelegate'], function() {
    App.Delegate.CarroComprasDelegate = App.Delegate._CarroComprasDelegate.extend({
        //Función que obtiene el usuario logeado. si no lo está, retorna null.
        getLogedUser: function(sdata, callback, callbackError) {
            $.ajax({
                url: '/carrocompras.master.service.subsystem/webresources/auth/session/user',
                method: 'GET',
                dataType: "html",
                contentType: "application/json"
            }).done(function(data) {
                callback(data);
            }).fail(function(error) {
                callbackError(error);
            });
        },
        //Función que obtiene una lista con los carros de compras de un usuario por el nombre.
        getClientCarts: function(sdata, callback, callbackError) {
            $.ajax({
                url: '../carrocompras.service.subsystem.web/webresources/CarroCompras/cliente/'+sdata,
                method: 'GET',
                dataType: "json",
                contentType: "application/json"
            }).done(function(data) {
                callback(data);
            }).fail(function(error) {
                callbackError(error);
            });
        },
        //Función que obtiene un cliente dado su nombre.
        getClientByName: function(sdata, callback, callbackError) {
            $.ajax({
                url: '../cliente.service.subsystem.web/webresources/Cliente/servicejson/'+sdata,
                method: 'GET',
                dataType: "json",
                contentType: "application/json"
            }).done(function(data) {
                callback(data);
            }).fail(function(error) {
                callbackError(error);
            });
        }
    });
});