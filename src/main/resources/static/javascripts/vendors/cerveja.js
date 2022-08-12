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

Cerveja.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

Cerveja.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

Cerveja.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

$(function() {
	var maskNumber = new Cerveja.MaskMoney();
	maskNumber.enable();
	
	var maskPhoneNumber = new Cerveja.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCep = new Cerveja.MaskCep();
	maskCep.enable();
	
	var maskDate = new Cerveja.MaskDate();
	maskDate.enable();
	
	var security = new Cerveja.Security();
	security.enable();
});