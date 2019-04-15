var Brewer = Brewer || {};

Brewer.MascaraCpfCnpj = (function(){
	function MascaraCpfCnpj(){
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('[for=cpfcnpj]');
		this.inputCpfCnpj = $('#cpfcnpj');
	}
	
	MascaraCpfCnpj.prototype.iniciar = function(){
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
	}
	
	function onTipoPessoaAlterado(evento){
		var tipoPessoaSelecionar = $(evento.currentTarget);
		
		this.labelCpfCnpj.text(tipoPessoaSelecionar.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionar.data('mascara'));
		this.inputCpfCnpj.val('')
		this.inputCpfCnpj.removeAttr('disabled');
		
	}
	
	return MascaraCpfCnpj;
}());

$(function(){
	var mascaraCpfCnpj = new Brewer.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
})