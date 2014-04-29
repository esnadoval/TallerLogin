define([], function() {
    App.Delegate.LoginDelegate = {
        //Función que obtiene el "user Stack" del servicio rest.
        getLogedUserStack: function(sdata, callback, callbackError) {
            $.ajax({
                url: '/carrocompras.master.service.subsystem/webresources/auth/session/userstack',
                method: 'GET',
                dataType: "html",
                contentType: "application/json"
            }).done(function(data) {
                callback(data);
            }).fail(function(error) {
                callbackError(error);
            });
        },
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
        }
    };
 
});

