# FileUploadApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**uploadMultiple**](#uploadmultiple) | **POST** /api/files/upload-multiple | |
|[**uploadSingle**](#uploadsingle) | **POST** /api/files/upload | |

# **uploadMultiple**
> CustomApiResponseListString uploadMultiple()


### Example

```typescript
import {
    FileUploadApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FileUploadApi(configuration);

let entityType: string; // (default to undefined)
let entityId: string; // (default to undefined)
let files: Array<File>; // (default to undefined)

const { status, data } = await apiInstance.uploadMultiple(
    entityType,
    entityId,
    files
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **entityType** | [**string**] |  | defaults to undefined|
| **entityId** | [**string**] |  | defaults to undefined|
| **files** | **Array&lt;File&gt;** |  | defaults to undefined|


### Return type

**CustomApiResponseListString**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **uploadSingle**
> CustomApiResponseString uploadSingle()


### Example

```typescript
import {
    FileUploadApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FileUploadApi(configuration);

let entityType: string; // (default to undefined)
let entityId: string; // (default to undefined)
let file: File; // (default to undefined)

const { status, data } = await apiInstance.uploadSingle(
    entityType,
    entityId,
    file
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **entityType** | [**string**] |  | defaults to undefined|
| **entityId** | [**string**] |  | defaults to undefined|
| **file** | [**File**] |  | defaults to undefined|


### Return type

**CustomApiResponseString**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

