define([], function() {
    App.Delegate.LoginDelegate = {
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

