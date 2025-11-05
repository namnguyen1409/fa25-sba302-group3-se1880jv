# PatientManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**checkPatientExistence**](PatientManagementApi.md#checkpatientexistence) | **GET** /api/patients/check |  |
| [**createAllergy**](PatientManagementApi.md#createallergy) | **POST** /api/patients/{patientId}/allergies |  |
| [**createEmergencyContact**](PatientManagementApi.md#createemergencycontact) | **POST** /api/patients/{patientId}/emergency-contacts |  |
| [**createPatient**](PatientManagementApi.md#createpatient) | **POST** /api/patients |  |
| [**deleteAllergy**](PatientManagementApi.md#deleteallergy) | **DELETE** /api/patients/allergies/{allergyId} |  |
| [**deleteEmergencyContact**](PatientManagementApi.md#deleteemergencycontact) | **DELETE** /api/patients/emergency-contacts/{contactId} |  |
| [**deletePatient**](PatientManagementApi.md#deletepatient) | **DELETE** /api/patients/{patientId} |  |
| [**filter**](PatientManagementApi.md#filter) | **POST** /api/patients/filter |  |
| [**filterAllergies**](PatientManagementApi.md#filterallergies) | **POST** /api/patients/{patientId}/allergies/filter |  |
| [**filterEmergencyContacts**](PatientManagementApi.md#filteremergencycontacts) | **POST** /api/patients/{patientId}/emergency-contacts/filter |  |
| [**getPatientById**](PatientManagementApi.md#getpatientbyid) | **GET** /api/patients/{patientId} |  |
| [**updateAllergy**](PatientManagementApi.md#updateallergy) | **PUT** /api/patients/allergies/{allergyId} |  |
| [**updateEmergencyContact**](PatientManagementApi.md#updateemergencycontact) | **PUT** /api/patients/emergency-contacts/{contactId} |  |
| [**updatePatient**](PatientManagementApi.md#updatepatient) | **PUT** /api/patients/{patientId} |  |



## checkPatientExistence

> CustomApiResponseBoolean checkPatientExistence(phone, email)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { CheckPatientExistenceRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string (optional)
    phone: phone_example,
    // string (optional)
    email: email_example,
  } satisfies CheckPatientExistenceRequest;

  try {
    const data = await api.checkPatientExistence(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **phone** | `string` |  | [Optional] [Defaults to `undefined`] |
| **email** | `string` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CustomApiResponseBoolean**](CustomApiResponseBoolean.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createAllergy

> CustomApiResponseAllergyResponse createAllergy(patientId, allergyRequest)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { CreateAllergyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // AllergyRequest
    allergyRequest: ...,
  } satisfies CreateAllergyRequest;

  try {
    const data = await api.createAllergy(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |
| **allergyRequest** | [AllergyRequest](AllergyRequest.md) |  | |

### Return type

[**CustomApiResponseAllergyResponse**](CustomApiResponseAllergyResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createEmergencyContact

> CustomApiResponseEmergencyContactResponse createEmergencyContact(patientId, emergencyContactRequest)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { CreateEmergencyContactRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // EmergencyContactRequest
    emergencyContactRequest: ...,
  } satisfies CreateEmergencyContactRequest;

  try {
    const data = await api.createEmergencyContact(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |
| **emergencyContactRequest** | [EmergencyContactRequest](EmergencyContactRequest.md) |  | |

### Return type

[**CustomApiResponseEmergencyContactResponse**](CustomApiResponseEmergencyContactResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createPatient

> CustomApiResponsePatientResponse createPatient(patientRequest)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { CreatePatientRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // PatientRequest
    patientRequest: ...,
  } satisfies CreatePatientRequest;

  try {
    const data = await api.createPatient(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientRequest** | [PatientRequest](PatientRequest.md) |  | |

### Return type

[**CustomApiResponsePatientResponse**](CustomApiResponsePatientResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteAllergy

> CustomApiResponseVoid deleteAllergy(allergyId)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { DeleteAllergyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    allergyId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteAllergyRequest;

  try {
    const data = await api.deleteAllergy(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **allergyId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteEmergencyContact

> CustomApiResponseVoid deleteEmergencyContact(contactId)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { DeleteEmergencyContactRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    contactId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteEmergencyContactRequest;

  try {
    const data = await api.deleteEmergencyContact(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **contactId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deletePatient

> CustomApiResponseVoid deletePatient(patientId)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { DeletePatientRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeletePatientRequest;

  try {
    const data = await api.deletePatient(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## filter

> CustomApiResponsePagePatientResponse filter(searchFilter)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { FilterRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterRequest;

  try {
    const data = await api.filter(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePagePatientResponse**](CustomApiResponsePagePatientResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## filterAllergies

> CustomApiResponsePageAllergyResponse filterAllergies(patientId, searchFilter)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { FilterAllergiesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterAllergiesRequest;

  try {
    const data = await api.filterAllergies(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageAllergyResponse**](CustomApiResponsePageAllergyResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## filterEmergencyContacts

> CustomApiResponsePageEmergencyContactResponse filterEmergencyContacts(patientId, searchFilter)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { FilterEmergencyContactsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterEmergencyContactsRequest;

  try {
    const data = await api.filterEmergencyContacts(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageEmergencyContactResponse**](CustomApiResponsePageEmergencyContactResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getPatientById

> CustomApiResponsePatientResponse getPatientById(patientId)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { GetPatientByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetPatientByIdRequest;

  try {
    const data = await api.getPatientById(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponsePatientResponse**](CustomApiResponsePatientResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateAllergy

> CustomApiResponseAllergyResponse updateAllergy(allergyId, allergyRequest)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { UpdateAllergyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    allergyId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // AllergyRequest
    allergyRequest: ...,
  } satisfies UpdateAllergyRequest;

  try {
    const data = await api.updateAllergy(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **allergyId** | `string` |  | [Defaults to `undefined`] |
| **allergyRequest** | [AllergyRequest](AllergyRequest.md) |  | |

### Return type

[**CustomApiResponseAllergyResponse**](CustomApiResponseAllergyResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateEmergencyContact

> CustomApiResponseEmergencyContactResponse updateEmergencyContact(contactId, emergencyContactRequest)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { UpdateEmergencyContactRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    contactId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // EmergencyContactRequest
    emergencyContactRequest: ...,
  } satisfies UpdateEmergencyContactRequest;

  try {
    const data = await api.updateEmergencyContact(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **contactId** | `string` |  | [Defaults to `undefined`] |
| **emergencyContactRequest** | [EmergencyContactRequest](EmergencyContactRequest.md) |  | |

### Return type

[**CustomApiResponseEmergencyContactResponse**](CustomApiResponseEmergencyContactResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updatePatient

> CustomApiResponsePatientResponse updatePatient(patientId, patientRequest)



### Example

```ts
import {
  Configuration,
  PatientManagementApi,
} from '';
import type { UpdatePatientRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PatientManagementApi(config);

  const body = {
    // string
    patientId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // PatientRequest
    patientRequest: ...,
  } satisfies UpdatePatientRequest;

  try {
    const data = await api.updatePatient(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **patientId** | `string` |  | [Defaults to `undefined`] |
| **patientRequest** | [PatientRequest](PatientRequest.md) |  | |

### Return type

[**CustomApiResponsePatientResponse**](CustomApiResponsePatientResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

