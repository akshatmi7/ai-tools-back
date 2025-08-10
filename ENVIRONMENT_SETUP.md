# Environment Variables Setup Guide

This Spring Boot application uses environment variables for configuration to enhance security and deployment flexibility.

## 🔧 Required Environment Variables

### Database Configuration
```bash
DATABASE_URL=jdbc:mysql://your-db-host:3306/database_name?createDatabaseIfNotExist=true&useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true
DATABASE_USERNAME=your-db-username
DATABASE_PASSWORD=your-secure-db-password
DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
```

### Security Configuration
```bash
JWT_SECRET=your-super-secure-jwt-secret-key-here-make-it-long-and-random
JWT_EXPIRATION=86400000
```

### External API Keys
```bash
GEMINI_API_KEY=your-gemini-api-key
```

### Google OAuth (Optional)
```bash
GOOGLE_CLIENT_ID=your-google-oauth-client-id
GOOGLE_CLIENT_SECRET=your-google-oauth-client-secret
GOOGLE_REDIRECT_URI=http://localhost:8080/api/auth/google/callback
```

## 🚀 Setup Methods

### Method 1: Create `.env` File (Development)
1. Copy `env.example` to `.env`:
   ```bash
   cp env.example .env
   ```
2. Edit `.env` with your actual values
3. Add `.env` to your `.gitignore` file

### Method 2: System Environment Variables
```bash
# Windows (PowerShell)
$env:DATABASE_PASSWORD="your-password"
$env:JWT_SECRET="your-jwt-secret"

# Windows (Command Prompt)
set DATABASE_PASSWORD=your-password
set JWT_SECRET=your-jwt-secret

# Linux/Mac
export DATABASE_PASSWORD="your-password"
export JWT_SECRET="your-jwt-secret"
```

### Method 3: IDE Configuration
**IntelliJ IDEA:**
1. Run Configuration → Environment Variables
2. Add each variable

**VS Code:**
1. Create `.vscode/launch.json`
2. Add env variables in configuration

### Method 4: Docker Environment
```dockerfile
# In docker-compose.yml
environment:
  - DATABASE_PASSWORD=your-password
  - JWT_SECRET=your-jwt-secret
```

### Method 5: Production Deployment
**AWS:**
- Use AWS Systems Manager Parameter Store
- Set environment variables in Elastic Beanstalk
- Use AWS Secrets Manager for sensitive data

**Heroku:**
```bash
heroku config:set DATABASE_PASSWORD=your-password
heroku config:set JWT_SECRET=your-jwt-secret
```

## 🔒 Security Best Practices

### 1. Strong Passwords
- Database password: At least 16 characters, mixed case, numbers, symbols
- JWT secret: At least 32 characters, completely random

### 2. Environment-Specific Values
- **Development**: Use different databases and keys
- **Production**: Use production-grade secrets

### 3. Secret Management
- Never commit secrets to version control
- Use secret management services in production
- Rotate secrets regularly

### 4. Validation
```bash
# Test database connection
echo $DATABASE_PASSWORD | wc -c  # Should be > 16

# Test JWT secret strength
echo $JWT_SECRET | wc -c  # Should be > 32
```

## 🛠️ Troubleshooting

### Common Issues:

1. **Environment variable not found**
   ```
   Error: Could not resolve placeholder 'DATABASE_PASSWORD'
   ```
   **Solution**: Ensure the environment variable is set correctly

2. **Database connection fails**
   ```
   Error: Access denied for user 'admin'@'host'
   ```
   **Solution**: Check DATABASE_USERNAME and DATABASE_PASSWORD

3. **JWT errors**
   ```
   Error: JWT secret is too short
   ```
   **Solution**: Use a longer JWT_SECRET (minimum 32 characters)

### Verification Commands:
```bash
# Check if variables are set
echo $DATABASE_PASSWORD
echo $JWT_SECRET

# List all environment variables
env | grep -E "(DATABASE|JWT|GEMINI)"
```

## 📋 Quick Setup Checklist

- [ ] Copy `env.example` to `.env`
- [ ] Set `DATABASE_PASSWORD` with your RDS password
- [ ] Generate strong `JWT_SECRET` (32+ characters)
- [ ] Set `GEMINI_API_KEY` if using AI features
- [ ] Configure `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` for OAuth
- [ ] Add `.env` to `.gitignore`
- [ ] Test application startup
- [ ] Verify database connection

## 🔄 Migration from Hardcoded Values

Your `application.properties` now uses this format:
```properties
spring.datasource.password=${DATABASE_PASSWORD:default-fallback-value}
```

This means:
- **Primary**: Uses `DATABASE_PASSWORD` environment variable
- **Fallback**: Uses the value after `:` if environment variable is not set

All sensitive values should be moved to environment variables for production use. 