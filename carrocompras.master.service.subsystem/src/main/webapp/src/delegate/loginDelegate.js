define([], function() {
    App.Delegate.LoginDelegate = {
        //Funci�n que obtiene el "user Stack" del servicio rest.
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
        //Funci�n que obtiene el usuario logeado. si no lo est�, retorna null.
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

