# Guide d'utilisation du Javadoc Fixer Maven Plugin

## 🚀 Installation et Premier Build

### 1. Compiler le plugin

Avant d'utiliser le plugin, vous devez le compiler et l'installer dans votre repository Maven local :

```bash
# Depuis la racine du projet
mvn clean install -pl javadoc-fixer-maven-plugin -DskipTests
```

### 2. Vérifier l'installation

```bash
# Vérifier que le plugin est bien installé
mvn help:describe -Dplugin=com.weedow:javadoc-fixer-maven-plugin:0.11.1-SNAPSHOT
```

## 📋 Utilisation

### Tester le plugin sur le module commons

```bash
# Nettoyer et générer les sources avec le profil release
mvn clean generate-sources -pl commons -P release

# Vérifier que les fichiers délombokisés ont été corrigés
cat commons/target/delombok/com/weedow/schemaorg/commons/model/SchemaDataType.java
```

### Générer la javadoc complète

```bash
# Générer la javadoc avec le profil release
mvn clean javadoc:javadoc -pl commons -P release

# Vérifier qu'il n'y a plus de warnings
```

### Tester sur tous les modules

```bash
# Build complet avec profil release
mvn clean install -P release

# Vérifier les logs pour voir les fixes appliqués
```

## 🔍 Vérification des Résultats

### Avant le fix

```java
/**
 * Data type name
 */
@java.lang.SuppressWarnings("all")
@lombok.Generated
public String getName() {
    return this.name;
}
```

**Problème** : Manque le tag `@return`

### Après le fix

```java
/**
 * Data type name
 * @return Data type name
 */
@java.lang.SuppressWarnings("all")
@lombok.Generated
public String getName() {
    return this.name;
}
```

**Résultat** : Tag `@return` ajouté automatiquement

## 🧪 Tests

### Exécuter les tests unitaires du plugin

```bash
mvn test -pl javadoc-fixer-maven-plugin
```

### Exécuter les tests avec verbose

```bash
mvn test -pl javadoc-fixer-maven-plugin -X
```

## ⚙️ Configuration Avancée

### Désactiver le plugin temporairement

```bash
mvn clean install -P release -Djavadoc.fixer.skip=true
```

### Changer le répertoire source

Dans votre `pom.xml` :

```xml
<plugin>
    <groupId>com.weedow</groupId>
    <artifactId>javadoc-fixer-maven-plugin</artifactId>
    <version>${project.version}</version>
    <configuration>
        <sourceDirectory>${project.build.directory}/custom-delombok</sourceDirectory>
    </configuration>
    <executions>
        <execution>
            <id>fix-javadoc</id>
            <phase>generate-sources</phase>
            <goals>
                <goal>fix-javadoc</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## 📊 Logs et Debugging

### Niveau de log normal

```bash
mvn clean generate-sources -pl commons -P release
```

Sortie :
```
[INFO] Fixing Javadoc in: /path/to/commons/target/delombok
[INFO] Fixed 15 Javadoc issues in 5 files
```

### Niveau de log debug

```bash
mvn clean generate-sources -pl commons -P release -X
```

Sortie détaillée :
```
[DEBUG] Fixed 3 Javadoc issues in: SchemaDataType.java
[DEBUG] Fixed 2 Javadoc issues in: JsonLdNode.java
...
```

## 🔧 Troubleshooting

### Le plugin ne trouve pas les fichiers

**Problème** : `Source directory does not exist: /path/to/delombok`

**Solution** : Vérifiez que lombok-maven-plugin s'exécute avant javadoc-fixer-maven-plugin :

```xml
<!-- L'ordre dans le pom.xml est important -->
<plugin>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok-maven-plugin</artifactId>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <goals>
                <goal>delombok</goal>
            </goals>
        </execution>
    </executions>
</plugin>

<plugin>
    <groupId>com.weedow</groupId>
    <artifactId>javadoc-fixer-maven-plugin</artifactId>
    <!-- Doit s'exécuter APRÈS delombok -->
</plugin>
```

### Les fixes ne sont pas appliqués

**Problème** : Les fichiers ne sont pas modifiés

**Solutions** :
1. Vérifier que les méthodes ont bien la javadoc incomplète (avec description mais sans @return/@param)
2. Vérifier que les méthodes sont annotées avec `@lombok.Generated`
3. Activer le mode debug pour voir les détails

### Warnings Javadoc persistent

**Problème** : Toujours des warnings après exécution

**Solutions** :
1. Nettoyer complètement : `mvn clean`
2. Vérifier que le plugin s'exécute bien dans les logs
3. Vérifier manuellement les fichiers dans `target/delombok/`

## 📈 Métriques

Le plugin affiche un résumé à la fin :

```
[INFO] Fixed 42 Javadoc issues in 12 files
```

Cela signifie :
- 42 tags (@return ou @param) ont été ajoutés
- 12 fichiers Java ont été modifiés

## 🎯 Intégration CI/CD

### Dans un pipeline Maven

```bash
# Build de release avec génération de javadoc
mvn clean deploy -P release

# Le plugin s'exécute automatiquement et corrige la javadoc
```

### Vérification post-build

```bash
# Vérifier qu'il n'y a pas de warnings javadoc
mvn javadoc:javadoc -P release | grep -i warning
```

Si aucune sortie, c'est parfait ! ✅

## 🆘 Support

En cas de problème :

1. Vérifier les logs en mode debug (`-X`)
2. Examiner les fichiers dans `target/delombok/`
3. Consulter les tests unitaires du plugin pour des exemples
4. Ouvrir une issue sur GitHub avec :
   - Version Maven
   - Version Java
   - Extrait du fichier problématique
   - Logs complets
