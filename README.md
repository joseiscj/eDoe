![Captura+de+tela+de+2019-08-26+16-27-24_upscaled_image_x4](https://user-images.githubusercontent.com/20418546/63717895-acca6f00-c81f-11e9-8ca9-52d5a4b4eb79.jpg)

# eDoe
# Descrição
  Muitas pessoas tem interesse em fazer doações, mas as vezes não tem o tempo necessário para encontrar onde doar ou como doar. Como sabemos, vivemos em um país em que a desigualdade social ainda existe e por isso há várias pessoas necessitadas, às vezes grupos de pessoas com um problema em comum. 
  
  No eDoe.com usuários de toda a região envolvida podem cadastrar itens a serem doados. Usuários também podem cadastrar itens necessários e o sistema deve saber casar doações com necessidades para facilitar a doação. Uma vez fechada uma doação, o sistema ajuda a organizar o encontro de doador/receptor.
  
  [Descrição completa do projeto](https://docs.google.com/document/d/e/2PACX-1vST2TI5lDbtMlv8rhFYJkYnrfgqzyWDv6DDvvAajz3_KK4tAs_UnAbYdI6oeMQA6jEHo5HwUAatHmd8/pub)
  
  # Diagrama
  [Diagrama ER](https://www.lucidchart.com/invitations/accept/e59ce12a-a45c-4c78-995e-0984821c3a90)
  
  # Pré requisitos
   O Sistema Operacional usado foi o *Linux* e as tecnologias utilizadas foram a LP *Java*, *Spring Boot* e a API *Postman* para testar o serviço RESTful por meio do envio de requisições HTTP e da análise do seu retorno. 
   
   Utilizamos o *Spring JPA* para integrarmos os objetos Java em um banco de dados, possibilitando a persistência dos mesmos. A IDE utilizada foi o *Eclipse IDE*.
   
  # Segurança
  ## Estratégia de Autenticação:
      A autenticação foi baseada em JWT, de forma que a cada requisição é preciso um token válido para se ter acesso. Todos     os endpoints estão bloqueados, somente os endpoints de login e criação de usuário estão abertos.

  Toda a segurança do sistema foi feita usando-se Spring Security, para todas as requisições, foi criada um interceptador, onde verifica o token e o usuário que está usando a API. Todas as senhas antes de irem para o BD, são criptografadas, usando a biblioteca Bcrypt, e só depois de criptografada, a senha é salva no BD, pra isso foi usado uma classe Listener para o usuário, para que antes da criação do usuário ou edição da senha do usuário, a senha seja sempre criptografada, antes de chegar ao BD.
  
  ## Para a autenticação:
 ```
      1) acesse a rota base_url/usuario/login com um Json contendo o Password e o Username.
      2) A credenciais estando corretas, acesse o Response headers, e copie o token, contido em "Authorization:".
     3) Esse token será o responsável por todo acesso a API.
        3.1 - O token terá validade durante 6 horas.
     4) Adicione esse token ao Authorize da sua aplicação segundo o seguinte padrão:
        Bearer <Token>
```
    
 # Desempenho
 ## Otimização via sistema de Cache
 O sistema oferece sistema de cache, usando a ferramenta de cache do Spring, o spring-boot-starter-cache, para otimizar todas requisições a API.
 
 ## Testes de desempenho:
 Todos os testes foram feitos usados a ferramenta JMeter.

  # Instalação
   Primeiro, é necessário **clonar** o [projeto do GitHub](https://github.com/joseiscj/eDoe.git), baixá-lo como **projeto Maven**, editá-lo em uma **IDE** de sua preferência (Eclipse STS, IntelliJ, etc) e, em seguida, selecionar o projeto e dar *run* para que o mesmo seja executado.
   
# Autoria e contribuições
  Os desenvolvedores do projeto são **José Ivan Silva da Cruz Júnior** (joseiscj) e **Marcos César** (Marcoozvn).
  
# Kubernetes
It was built a single image container on a **VM**, where it was able to deploy the app and test the api running on other machine.
### Install on your machine:
```
1. minikube
2. kubectl
3. dockertools
4. Oracle VM Virtualbox
```

## Running with Kubernetes
### 1. Build the app and the Docker image
```
eval $(minikube docker-env)
./mvnw clean package
docker build -t simple-crud:0.0.1 .

```
### 2. Run a Kubernetes deployment on the running Minikube cluster

```
kubectl run simple-crud --image simple-crud:0.0.1 --port 8080
kubectl expose deployment simple-crud --type=NodePort
```
### 3. Test the app
```
curl $(minikube service simple-crud --url)/users/kubernetes
```
### 4. Create Deployment and Service YAML files for future repeatable deployments
```
kubectl run simple-crud --image simple-crud:0.0.1 --port 8080 -o yaml --dry-run \
    > simple-crud-deployment.yaml
kubectl expose deployment simple-crud --type=NodePort -o yaml --dry-run > simple-crud-svc.yaml
```   
### 5. Delete resources created for simple-crud
```
kubectl delete all -l run=simple-crud
```
 
