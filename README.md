# LW ACCOUNT HOLDER

## Projeto utilizado para aplicar clean architecture

Esse projeto simula uma pequena regra de negócio, utilizado por um banco virtual para abertura de conta corrente PJ.

### Caso de Uso

Cliente está abrindo uma conta corrente PJ. Criaremos um serviço que validará se o cliente já tem uma conta corrente PF
ativa. Se sim, esse cliente é considerado um cliente ativo. O cliente sendo ativo, não passará por nenhum validação de
anti-fraude.

#### O que será implementado?

- [ ] Validar se o cliente tem uma conta corrente PF ativa
- [ ] Validar se o cliente é titular da conta corrente PF
- [ ] Validar se a conta corrente PF, está aberta a mais de 179 dias
- [ ] Validar se a última movimentação do cliente foi realizado no últimos 90 dias
- [ ] Validar se a conta esta aberta para outros produtos.

Se todas essas validações forrem verdadeira o cliente é considerado ativo. No nosso caso a conta poderá ter mais de um
titular.

#### Haverá outros serviços?

Sim, haverá um serviço que buscará um lista de titulares da conta. Esse caso é quando a conta corrente PF é uma conta
conjunta ou conta com vários sócios. Outro serviço buscar as conta corrente PF do cliente.

#### Utilizares qual architecture?

Nos basearemos em clean architecture.
![enter image description here](https://cdn-media-1.freecodecamp.org/images/1*nEATDe5dRLIWN3MSxSjG0A.png)

#### Quais os dados de entrada?

- Documento de identificação(CPF)
- Código único do cliente no Banco.(Todo cliente tem um código único no banco)

#### Quais os dados de saída?

- Tipo de cliente: NC=new customer, IC=inactive customer ou AC= active customer
- Documento de identificação(CPF)
- Código unido do cliente no banco.

#### Qual a linguagem de programação utilizaremos?

Nesse pequeno projeto, utilizaremos apenas Java na versão 17.
