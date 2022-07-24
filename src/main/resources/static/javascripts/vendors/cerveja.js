var Cerveja = Cerveja || {};

Cerveja.MaskMoney = (function() {
	
	function MaskMoney() {
	this.decimal = $('.js-decimal');
	this.plain = $('.js-plain');
		
	}
	
	MaskMoney.prototype.enable = function() {		
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.plain.maskMoney({ precision: 0, thousands: '.' });
	}
	return MaskMoney;
	
}());

Cerveja.MaskPhoneNumber = (function(){
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var SPMaskBehavior = function(val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(000) 0000-00009';
		},
		spOptions = {
			onKeyPress: function(val, e, field, options) {
				field.mask(SPMaskBehavior.apply({}, arguments), options);
			}
		};	
		
		this.inputPhoneNumber.mask(SPMaskBehavior, spOptions);
	}
	
	return MaskPhoneNumber;
	
}());

$(function() {
	var maskNumber = new Cerveja.MaskMoney();
	maskNumber.enable();
	
	var maskPhoneNumber = new Cerveja.MaskPhoneNumber();
	maskPhoneNumber.enable();
});