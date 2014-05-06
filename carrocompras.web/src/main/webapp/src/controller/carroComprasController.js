define(['controller/_carroComprasController', 'delegate/carroComprasDelegate'], function() {
    App.Controller.CarroComprasController = App.Controller._CarroComprasController.extend({
        list: function(params) {

            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' + 'instead-carroCompras-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-list', {view: this, data: data});
                var self = this;
                if (!this.carroComprasModelList) {
                    this.carroComprasModelList = new this.listModelClass();
                }

                var ctrl = this;
                App.Delegate.CarroComprasDelegate.prototype.getLogedUser("", function(data) {

                    App.Delegate.CarroComprasDelegate.prototype.getClientCarts(data, function(data2) {
                        ctrl.carroComprasModelList.fetch({
                            data: data2,
                            success: function() {
                                self._renderList();
                                Backbone.trigger(self.componentId + '-' + 'post-carroCompras-list', {view: self});
                            },
                            error: function(mode, error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-list', view: self, error: error});
                            }

                        });
                    }, function(data3) {

                    });

                }, function(data4) {


                });

            }
        }
    });
    return App.Controller.CarroComprasController;
}); 