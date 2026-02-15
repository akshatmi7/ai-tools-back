# 🚀 AWS RDS MySQL Deployment Guide

## 📋 **Required Environment Variables for Render**

Based on your Render environment configuration, you need these environment variables:

### 🔐 **Database Configuration**
```
DB_HOST=aiintelli-db.cz86ouoq2yzz.ap-south-1.rds.amazonaws.com
DB_PORT=3306
DB_USERNAME=admin
DB_PASSWORD=Akshat1292992
```

### 🔑 **JWT Configuration**
```
JWT_SECRET=your-super-secure-jwt-secret-key-here-make-it-long-and-random
```

### 🤖 **External API Keys**
```
GEMINI_API_KEY=AIzaSyCrTJGNbrRTD7clFEKJfsgytplJ-wWAFHY
```

### 🔗 **Google OAuth (Optional - for future use)**
```
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret
GOOGLE_REDIRECT_URI=https://your-app-name.onrender.com/api/auth/google/callback
```

## 🛠️ **How to Set Environment Variables in Render:**

1. **Go to your Render Dashboard**
2. **Select your "ai-tools-backend" service**
3. **Go to "Environment" tab**
4. **Add each variable above**
5. **Click "Save Changes"**
6. **Redeploy your service**

## ✅ **What's Now Hardcoded in application.properties:**

- ✅ **Database URL structure** (uses environment variables for host, port, username, password)
- ✅ **Connection pool settings** (HikariCP optimized for production)
- ✅ **Hibernate/JPA settings** (batch processing, dialect, etc.)
- ✅ **Server configuration** (port, context path)
- ✅ **Logging levels** (production-optimized)
- ✅ **CORS settings** (for frontend integration)
- ✅ **Actuator endpoints** (health checks, metrics)

## 🔍 **Production-Ready Features:**

### ✅ **Database Connection**
- **SSL enabled** with fallback to non-SSL
- **Connection pooling** optimized for AWS RDS
- **Auto-reconnect** and failover handling
- **Proper timezone** and character encoding
- **Connection timeouts** and validation

### ✅ **Performance Optimizations**
- **Hibernate batch processing** enabled
- **Connection pool tuning** for production
- **Query optimization** settings
- **Memory-efficient** configurations

### ✅ **Monitoring & Logging**
- **Health check endpoints** exposed
- **Metrics collection** enabled
- **Production logging** levels
- **Connection leak detection**

### ✅ **Security**
- **CORS configuration** for frontend
- **JWT security** settings
- **Database connection** security
- **Only sensitive data** in environment variables

## 🔍 **Troubleshooting Common Issues:**

### **❌ "Cannot load driver class" Error**
**Solution:** ✅ Fixed - MySQL dependency added to `pom.xml`

### **❌ "Connection refused" Error**
**Check:**
- AWS RDS instance is running
- Security groups allow connections from Render
- `DB_HOST` and `DB_PORT` are correct

### **❌ "Access denied" Error**
**Check:**
- `DB_USERNAME` and `DB_PASSWORD` are correct
- User has proper permissions on the database

### **❌ "SSL Connection" Error**
**Solution:** ✅ Fixed - SSL settings configured with fallback

### **❌ "Connection timeout" Error**
**Solution:** ✅ Fixed - Increased connection timeouts and added retry logic

## 📝 **Deployment Checklist:**

- [x] **MySQL dependency** added to `pom.xml`
- [x] **Environment variables** set in Render
- [x] **Connection pooling** optimized
- [x] **SSL settings** configured
- [x] **Health checks** enabled
- [x] **Logging** configured for production
- [x] **CORS** settings added
- [x] **Non-sensitive config** hardcoded

## 🚨 **Security Best Practices:**

- ✅ **Only sensitive data** in environment variables
- ✅ **Use strong JWT secrets** (32+ characters)
- ✅ **Rotate API keys** regularly
- ✅ **Monitor database connections**
- ✅ **Enable SSL** for database connections

## 🔧 **AWS RDS Security Group Configuration:**

Make sure your AWS RDS security group allows connections from Render's IP ranges:

1. **Go to AWS RDS Console**
2. **Select your database instance**
3. **Go to "Connectivity & security"**
4. **Click on the security group**
5. **Add inbound rule:**
   - **Type:** MySQL/Aurora (3306)
   - **Source:** 0.0.0.0/0 (or specific Render IP ranges)
   - **Description:** Render deployment

## 📊 **Monitoring Your Deployment:**

After deployment, you can monitor your application at:
- **Health Check:** `https://your-app.onrender.com/actuator/health`
- **Info:** `https://your-app.onrender.com/actuator/info`
- **Metrics:** `https://your-app.onrender.com/actuator/metrics`

## 🎯 **Next Steps:**

1. **Verify all environment variables** are set in Render
2. **Deploy your application**
3. **Test the health endpoint**
4. **Monitor logs** for any issues
5. **Test your API endpoints**

Your application is now configured for production deployment with AWS RDS MySQL! 🚀 