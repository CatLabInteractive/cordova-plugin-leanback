function Leanback() {

	this.hasLeanback = null;
	this.hasLeanbackOnly = null;

	this.checkHasLeanback(function (avail) {
		this.hasLeanback = avail;
	}.bind(this));

	this.checkHasLeanbackOnly(function (avail) {
		this.hasLeanbackOnly = avail;
	}.bind(this));

}

Leanback.prototype = {

	/**
	 * Determine if the device is a television
	 * @param callback
	 */
	isTelevision: function (callback) {
		if (this.hasLeanback !== null) {
			callback(this.hasLeanback);
			return;
		}

		this.checkHasLeanback(function (avail) {
			this.hasLeanback = avail;
			callback(avail);
		});
	},

	checkHasLeanback: function (callback) {
		cordova.exec(
			function (avail) {
				callback(avail == 1 ? true : false);
			},
			function(error) {
				console.error(error);
			},
			"Leanback",
			"hasLeanback"
		);
	},

	checkHasLeanbackOnly: function (callback) {
		cordova.exec(
			function (avail) {
				callback(avail == 1 ? true : false);
			},
			function(error) {
				console.error(error);
			},
			"Leanback",
			"hasLeanbackOnly"
		);
	}
};

Leanback.install = function () {
	if (!cordova.plugins) {
		cordova.plugins = {};
	}

	cordova.plugins.leanback = new Leanback();
	return cordova.plugins.leanback;
};

cordova.addConstructor(Leanback.install);
